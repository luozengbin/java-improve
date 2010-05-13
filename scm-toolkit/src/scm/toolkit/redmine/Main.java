package scm.toolkit.redmine;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.collections.keyvalue.DefaultKeyValue;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scm.toolkit.Constants;
import scm.toolkit.excel.ExcelReader;
import scm.toolkit.mail.MailAgent;
import scm.toolkit.mail.SimpleMailEntity;
import scm.toolkit.notes.NotesClientAgent;
import scm.toolkit.redmine.entity.CustomField;
import scm.toolkit.redmine.entity.CustomValue;
import scm.toolkit.redmine.entity.Enumeration;
import scm.toolkit.redmine.entity.Issue;
import scm.toolkit.redmine.entity.IssueCategory;
import scm.toolkit.redmine.entity.IssueStatus;
import scm.toolkit.redmine.entity.Journal;
import scm.toolkit.redmine.entity.JournalDetail;
import scm.toolkit.redmine.entity.Project;
import scm.toolkit.redmine.entity.Tracker;
import scm.toolkit.redmine.entity.User;
import scm.toolkit.redmine.entity.Version;
import scm.toolkit.redmine.entity.Watcher;
import scm.toolkit.util.CommonUtils;

public class Main {
	
	private static final Log log = LogFactory.getLog(Main.class);
	
	public static void main(String[] args) throws Exception {
		
		Reader reader = null;
		
		RedmineAgent redmineAgent = null;
		
		MailAgent mailAgent = null;
		
		Date now = null;
		Properties prop = null;
		
		try {
			
			prop = new Properties();
			//設定情報初期化
			
			reader = new InputStreamReader(new FileInputStream(System.getProperty(Constants.CONFIG_FILE_PATH)), System.getProperty(Constants.FILE_ENCODING));
			prop.load(reader);
			reader.close();
			reader = null;
			
			//ノーツからテスト管理票を取得
			NotesClientAgent.run(prop);
			
			//エクセルからデータを取出
			Map<String, Map<String, String>> data = ExcelReader.extractData(prop);
			
			redmineAgent = new RedmineAgent();
			redmineAgent.init();
			
			//DEFAULT項目値初期化
			Project defaultProject = redmineAgent.getEntity(Project.class,"getProjectByName", new DefaultKeyValue("name", prop.get(Constants.REDMINE_ISSUE_DEFAULT_PROJECT)));
			Tracker defaultTracker = redmineAgent.getEntity(Tracker.class,"getTrackerByName", new DefaultKeyValue("name", prop.get(Constants.REDMINE_ISSUE_DEFAULT_TRACKER)));
			Enumeration defaultPriority = redmineAgent.getEntity(Enumeration.class,"getEnumerationByName", new DefaultKeyValue("name", prop.get(Constants.REDMINE_ISSUE_DEFAULT_PRIORITY)));
			IssueCategory defaultIssueCategory = redmineAgent.getEntity(IssueCategory.class, "getIssueCategoryByName",new DefaultKeyValue("name", prop.get(Constants.REDMINE_ISSUE_DEFAULT_CATEGORY)));
			IssueStatus defaultIssueStatus = redmineAgent.getEntity(IssueStatus.class,"getIssueStatusByName", new DefaultKeyValue("name", prop.get(Constants.REDMINE_ISSUE_DEFAULT_STATUS)));
			Version defaultVersion = redmineAgent.getEntity(Version.class,"getVersionByName", new DefaultKeyValue("name", prop.get(Constants.REDMINE_ISSUE_DEFAULT_VERSION)));
			User author = redmineAgent.getEntity(User.class, "getUserByLastName",new DefaultKeyValue("lastname", prop.get(Constants.REDMINE_ISSUE_DEFAULT_AUTHOR)));
			
			List<User> defaultWatcherUsers = new ArrayList<User>();
			for (String watcherName : ((String)prop.get(Constants.REDMINE_ISSUE_DEFAULT_WATCHERS)).split(",")) {
				defaultWatcherUsers.add(redmineAgent.getEntity(User.class, "getUserByLastName",new DefaultKeyValue("lastname", watcherName)));
			}
			
			//カスタマイズ項目マスターデータ取得
			List<CustomField> customFieldList = redmineAgent.getEntityList(CustomField.class);
			Map<String, CustomField> customFieldMap = new HashMap<String, CustomField>();
			for (CustomField customField : customFieldList) {
				customFieldMap.put(customField.getName(), customField);
			}
			
			//クローズ確認用
			Map<String, IssueStatus> updateStatusMap = new HashMap<String, IssueStatus>();
			for (Entry<String, String> updateStatus : CommonUtils.splitAsMap((String)prop.getProperty(Constants.REDMINE_ISSUE_UPDATE_STATUS), ",", Constants.KEY_VALUE_PIPE_MARK).entrySet()) {
				
				updateStatusMap.put(updateStatus.getKey(), redmineAgent.getEntity(IssueStatus.class, "getIssueStatusByName", new DefaultKeyValue("name", updateStatus.getValue())));
			}
			
			//filter情報
			Map<String, String> filterMap = CommonUtils.splitAsMap((String)prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_FILTER), ",", Constants.KEY_VALUE_PIPE_MARK);
			
			//notesデータ→redmine項目値書き換え元情報
			Map<String, Map<String, String>> valueMap = new HashMap<String, Map<String,String>>();
			for (Entry<String, String> customValueMappingEntry : CommonUtils.splitAsMap((String)prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_VALUE_MAPPING), ",", Constants.KEY_VALUE_PIPE_MARK).entrySet()) {
				valueMap.put(customValueMappingEntry.getKey(), CommonUtils.splitAsMap(customValueMappingEntry.getValue(), ",", Constants.KEY_VALUE_SET_MARK));
			}
			
			//メール送信初期化
			mailAgent = new MailAgent(prop);
			mailAgent.setFrom(author.getMail());
			List<String> addrTo = new ArrayList<String>();
			for (User watcherUser : defaultWatcherUsers) {
				if(StringUtils.isNotEmpty(watcherUser.getMail())){
					addrTo.add(watcherUser.getMail());
				}
			}
			mailAgent.setTo(addrTo.toArray(new String[0]));
			mailAgent.setTitleTemplateName((String)prop.get(Constants.REDMINE_MAIL_NEW_ISSUE_TITLE_TEMPLATE_NAME));
			mailAgent.setBodyTemplateName((String)prop.get(Constants.REDMINE_MAIL_NEW_ISSUE_BODY_TEMPLATE_NAME));
			
			boolean skip = false;
			
			boolean updated= false;
			
			Map<String, List<Issue>> updatedIssue = new HashMap<String, List<Issue>>();
			
			for (Entry<String, Map<String, String>> entry : data.entrySet()) {
				
				now = Calendar.getInstance().getTime();
				
				skip = false;
				
				if(redmineAgent.getEntitys(CustomValue.class, "getCustomValueByManageNo", new DefaultKeyValue("manageNo", entry.getKey()), new DefaultKeyValue("customFieldName" ,prop.getProperty(Constants.REDMINE_SPREADSHEET_ISSUE_KEY))).size() <= 0){
					
					for (Entry<String, String> filterEntry : filterMap.entrySet()) {
						if(entry.getValue().containsKey(filterEntry.getKey())){
							if(entry.getValue().get(filterEntry.getKey()).indexOf(filterEntry.getValue()) < 0){
								skip = true;
								break;
							}
						}
					}
					
					if(skip){
						continue;
					}
					
					Issue newIssue = new Issue();
					newIssue.setSubject("#" + entry.getKey() + "が起票されました");
					newIssue.setDescription("テスト管理用から引用\n<pre>\n"
							+ "■" + prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_DESCRIPTION) + ":\n" 
							+ entry.getValue().get(prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_DESCRIPTION)) + "\n\n"
							+ "■" + prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_COMMENT) + ":\n"
							+ entry.getValue().get(prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_COMMENT)) + "\n"
							+ "</pre>");
					newIssue.setProject(defaultProject);
					newIssue.setTracker(defaultTracker);
					newIssue.setPriority(defaultPriority);
					
					if(entry.getValue().containsKey(prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_CATEGORY))){
						try {newIssue.setIssueCategory(redmineAgent.getEntity(IssueCategory.class, "getIssueCategoryByName",new DefaultKeyValue("name", entry.getValue().get(prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_CATEGORY)))));}
						catch(Exception ex){newIssue.setIssueCategory(defaultIssueCategory);}
					}else{
						newIssue.setIssueCategory(defaultIssueCategory);
					}
					
					newIssue.setIssueStatus(defaultIssueStatus);
					newIssue.setAuthor(author);
					newIssue.setFixVersion(defaultVersion);
					newIssue.setCreatedOn(now);
					newIssue.setUpdatedOn(now);
					newIssue.setStartDate(now);
					
					newIssue.setCustomValues(new ArrayList<CustomValue>());
					
					CustomValue customValue = null;
					
					entry.getValue().remove(prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_CATEGORY));
					entry.getValue().remove(prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_DESCRIPTION));
					entry.getValue().remove(prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_COMMENT));
					
					for (Entry<String, String> customFieldEntry : entry.getValue().entrySet()) {
						if(customFieldMap.containsKey(customFieldEntry.getKey())){
							customValue = new CustomValue();
							customValue.setCustomField(customFieldMap.get(customFieldEntry.getKey()));
							customValue.setIssue(newIssue);
							customValue.setCustomizedType(Constants.REDMINE_ISSUE);
							if(valueMap.containsKey(customFieldEntry.getKey()) && valueMap.get(customFieldEntry.getKey()).containsKey(customFieldEntry.getValue())){
								customValue.setValue(valueMap.get(customFieldEntry.getKey()).get(customFieldEntry.getValue()));
							}else{
								customValue.setValue(customFieldEntry.getValue());
							}
							newIssue.getCustomValues().add(customValue);
						}
					}
					
					newIssue.setWatchers(new ArrayList<Watcher>());
					
					Watcher watcher = null;
					for (User watcherUser : defaultWatcherUsers) {
						watcher = new Watcher();
						watcher.setWatchableType(Constants.REDMINE_ISSUE);
						watcher.setUser(watcherUser);
						watcher.setWatchableIssue(newIssue);
						newIssue.getWatchers().add(watcher);
					}
					
					redmineAgent.persistEntity(newIssue);
					
					//メール送信:起票
					mailAgent.send(newIssue);
					
					log.debug("#" + entry.getKey() + "が起票されました。\t障害番号：#" + entry.getKey() + "\tチケット番号：" + newIssue.getId());
				} else {
					
					//受付完了
					if(entry.getValue().containsKey(prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_CONFIRM_DATE))){
						
						List<Issue> issueList = redmineAgent.getEntitys(Issue.class, "getIssuesByManageNo", new DefaultKeyValue("manageNo", entry.getKey()));
						
						Journal journal = null;
						JournalDetail journalDetail = null;
						IssueStatus newIssueStatus = null;
						
						StringBuffer bf = null;new StringBuffer();
						
						for(Issue issue : issueList){
							
							bf = new StringBuffer();
							
							if(updateStatusMap.containsKey(issue.getIssueStatus().getName())){
								
								String oldValue = String.valueOf(issue.getIssueStatus().getId());
								
								newIssueStatus = updateStatusMap.get(issue.getIssueStatus().getName());
								
								issue.setUpdatedOn(now);
								issue.setLockVersion(issue.getLockVersion() + 1);
								issue.setIssueStatus(newIssueStatus);
								
								journal = new Journal();
								journal.setIssue(issue);
								journal.setUser(author);
								journal.setCreatedOn(now);
								journal.setJournalizedType(Constants.REDMINE_ISSUE);
								
								bf.append("■受入状況").append("<pre>\n");
								
								for (String confirmFiled : ((String)prop.get(Constants.REDMINE_SPREADSHEET_ISSUE_CONFIRM_FILEDS)).split(",")) {
									if(entry.getValue().containsKey(confirmFiled)){
										bf.append(confirmFiled).append(": ").append(entry.getValue().get(confirmFiled)).append("\n");
									}
								}
								
								bf.append("</pre>\n");
								
								journal.setNotes(bf.toString());
								
								journal.setJournalDetails(new ArrayList<JournalDetail>());
								journalDetail = new JournalDetail();
								journalDetail.setJournal(journal);
								journalDetail.setOldValue(oldValue);
								journalDetail.setValue(String.valueOf(issue.getIssueStatus().getId()));
								journalDetail.setProperty("attr");
								journalDetail.setPropKey("status_id");
								journal.getJournalDetails().add(journalDetail);
								redmineAgent.persistEntity(issue, journal);
								
								updated = true;
								
								if(!updatedIssue.containsKey(entry.getKey())){
									updatedIssue.put(entry.getKey(), new ArrayList<Issue>());
								}
								updatedIssue.get(entry.getKey()).add(issue);
								
								log.debug("#" + entry.getKey() + "受付が完了のため、チケット#" + issue.getId() + "のステータスを" + newIssueStatus.getName() + "に変更しました。");
							}
						}
					}
				}
			}
			
			//メール送信:ステータス更新通知
			if(updated){
				
				List<String> updateWatcherMailAddress = new ArrayList<String>();
				for(String updateWatcherName : ((String)prop.get(Constants.REDMINE_ISSUE_UPDATE_WATCHERS)).split(",")){
					updateWatcherMailAddress.add(redmineAgent.getEntity(User.class, "getUserByLastName",new DefaultKeyValue("lastname", updateWatcherName)).getMail());
				}
				mailAgent.setTo(updateWatcherMailAddress.toArray(new String[0]));
				
				SimpleMailEntity simpleMailEntity = new SimpleMailEntity();
				
				simpleMailEntity.setTitile(updatedIssue.keySet());
				simpleMailEntity.setBody(updatedIssue.values());
				
				mailAgent.setTitleTemplateName(prop.getProperty(Constants.REDMINE_MAIL_UPDATE_ISSUE_TITLE_TEMPLATE_NAME));
				mailAgent.setBodyTemplateName(prop.getProperty(Constants.REDMINE_MAIL_UPDATE_ISSUE_BODY_TEMPLATE_NAME));
				
				mailAgent.send(simpleMailEntity);
			}

		} catch (Exception e) {
			log.error(e);
			
			if(Boolean.valueOf((String)prop.get(Constants.FAIL_ALTER_ENABLE))){
				
				if(mailAgent == null){
					mailAgent = new MailAgent(prop);
				}
				mailAgent.setFrom((String)prop.get(Constants.FAIL_ALTER_MAIL_FROM));
				mailAgent.setTo((String)prop.get(Constants.FAIL_ALTER_MAIL_TO));
				
				mailAgent.setTitleTemplateName((String)prop.getProperty(Constants.FAIL_ALTER_MAIL_TITLE_TEMPLATE_NAME));
				mailAgent.setBodyTemplateName((String)prop.getProperty(Constants.FAIL_ALTER_MAIL_BODY_TEMPLATE_NAME));
				
				mailAgent.send(ExceptionUtils.getFullStackTrace(e));
				
			}
			
			throw e;
		}finally{
			if(reader != null){
				reader.close();
			}
			if(redmineAgent != null){
				redmineAgent.shutdown();
				redmineAgent = null;
			}
			mailAgent = null;
		}
	}
}
