package test;


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
import org.junit.Test;

import scm.toolkit.Constants;
import scm.toolkit.redmine.RedmineAgent;
import scm.toolkit.redmine.entity.Issue;
import scm.toolkit.redmine.entity.IssueStatus;
import scm.toolkit.redmine.entity.Journal;
import scm.toolkit.redmine.entity.JournalDetail;
import scm.toolkit.redmine.entity.User;
import scm.toolkit.util.CommonUtils;

public class MainTest {

	@Test
	public void runMain() throws Exception {
		
		Properties prop = new Properties();
		Reader reader = new InputStreamReader(new FileInputStream("C:/mywork/google-code/java-improve/trunk/scm-toolkit/config.properties"), "MS932");
		prop.load(reader);
		reader.close();
		reader = null;
		
		RedmineAgent redmineAgent = new RedmineAgent();
		redmineAgent.init();
		
		Map<String, IssueStatus> updateStatusMap = new HashMap<String, IssueStatus>();
		for (Entry<String, String> updateStatus : CommonUtils.splitAsMap((String)prop.getProperty(Constants.REDMINE_ISSUE_UPDATE_STATUS), ",", Constants.KEY_VALUE_PIPE_MARK).entrySet()) {
			
			updateStatusMap.put(updateStatus.getKey(), redmineAgent.getEntity(IssueStatus.class, "getIssueStatusByName", new DefaultKeyValue("name", updateStatus.getValue())));
		}
		
		List<Issue> issueList = redmineAgent.getEntitys(Issue.class, "getIssuesByManageNo", new DefaultKeyValue("manageNo", "2211"));
		
		Journal journal = null;
		JournalDetail journalDetail = null;
		
		Date now = Calendar.getInstance().getTime();
		
		for(Issue issue : issueList){
			
			if(updateStatusMap.containsKey(issue.getIssueStatus().getName())){
				
				String oldValue = String.valueOf(issue.getIssueStatus().getId());
				
				issue.setUpdatedOn(now);
				issue.setIssueStatus(updateStatusMap.get(issue.getIssueStatus().getName()));
				
				journal = new Journal();
				journal.setIssue(issue);
				journal.setCreatedOn(now);
				journal.setUser(redmineAgent.getEntity(User.class, "getUserByLastName",new DefaultKeyValue("lastname", prop.get(Constants.REDMINE_ISSUE_DEFAULT_AUTHOR))));
				
				journal.setJournalizedType(Constants.REDMINE_ISSUE);
				
				journal.setNotes("■受入状況");
				journal.setNotes("<pre>納品日:" + "2010/4/24" + "/n" + "確認者:" + "菊地" + "/n" + "完了日:" + "2010/4/24");
				
				journal.setJournalDetails(new ArrayList<JournalDetail>());
				journalDetail = new JournalDetail();
				journalDetail.setJournal(journal);
				
				journalDetail.setOldValue(oldValue);
				journalDetail.setValue(String.valueOf(issue.getIssueStatus().getId()));
				
				journalDetail.setProperty("attr");
				journalDetail.setPropKey("status_id");
				
				journal.getJournalDetails().add(journalDetail);
				
				redmineAgent.persistEntity(issue, journal);
			}
		}
		
		redmineAgent.shutdown();
		
	}

}
