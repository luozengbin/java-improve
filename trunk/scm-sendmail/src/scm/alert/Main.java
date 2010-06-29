package scm.alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scm.alert.mail.MailAgent;

public class Main {

	private static final Log log = LogFactory.getLog(Main.class);

	public static void main(String[] args) throws Exception {
		
		if (args.length != 3) {
			log.error("Usage: /home/admin/config.properties alert_illegal_url /home/admin/work/result.txt");
			return;
		}
		
		String configFilePath = args[0];
		String mailTemplate = args[1];
		String[] attachementFiles = args[2].split("[,]");
		String[] toMailAddr = null;

		Reader reader = null;
		Properties prop = null;
		
		MailAgent mailAgent = null;

		try {
			
			boolean isValidated = false;
			
			for (String file : attachementFiles) {
				reader = new InputStreamReader(new FileInputStream(file), System.getProperty(Constants.FILE_ENCODING));
				
				int emty =(new FileInputStream(new File(file))).available();
				
				if(emty > 0){
					isValidated = true;
				}
			}
			
			if(!isValidated){
				return;
			}
			
			reader = null;
			
			prop = new Properties();
			// ê›íËèÓïÒèâä˙âª
			reader = new InputStreamReader(new FileInputStream(configFilePath), System.getProperty(Constants.FILE_ENCODING));
			prop.load(reader);
			reader.close();
			reader = null;
			
			toMailAddr = prop.getProperty(Constants.MAIL_TO_ADDRESS).split("[,]");
			
			mailAgent = new MailAgent(prop);
			mailAgent.setFrom((String)prop.get(Constants.ADMIN_MAIL_ADDRESS));
			mailAgent.setTo(toMailAddr);
			mailAgent.setAttachementFiles(attachementFiles);
			mailAgent.setTitleTemplateName(mailTemplate + "_title.vm");
			mailAgent.setBodyTemplateName(mailTemplate + "_body.vm");
			
			mailAgent.send(new Object());
			
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
			mailAgent = null;
		}
	}
}
