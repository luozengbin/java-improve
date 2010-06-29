package test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import scm.toolkit.Constants;
import scm.toolkit.mail.MailAgent;
import scm.toolkit.redmine.entity.Issue;
import scm.toolkit.redmine.entity.Project;
import scm.toolkit.redmine.entity.Tracker;

public class MailAgentTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSend() throws Exception {
		
		Properties prop = new Properties();
		Reader reader = new InputStreamReader(new FileInputStream("C:\\mywork\\google-code\\java-improve\\trunk\\scm-toolkit\\config.properties"), System.getProperty(Constants.FILE_ENCODING));
		prop.load(reader);
		reader.close();
		reader = null;
		
		prop.put("FILE.resource.loader.path", "C:\\mywork\\google-code\\java-improve\\trunk\\scm-toolkit\\mailTemplate");
		
		MailAgent agent = new MailAgent(prop);
		
		agent.setFrom("zengbin_luo@ot.olympus.co.jp");
		agent.setTo("zengbin_luo@ot.olympus.co.jp");
		agent.setTitleTemplateName("new_issue_title.vm");
		agent.setBodyTemplateName("new_issue_body.vm");
		
		Issue issue = new Issue();
		
		issue.setId(111);
		issue.setSubject("#2232が起票されました");
		
		Project project = new Project();
		project.setName("ものづくり基盤");
		
		issue.setProject(project);
		
		Tracker tracker = new Tracker();
		
		tracker.setName("バグ");
		
		issue.setTracker(tracker);
		
		
		agent.send(issue);
		
	}

}
