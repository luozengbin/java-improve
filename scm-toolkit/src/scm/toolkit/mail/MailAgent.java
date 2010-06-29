package scm.toolkit.mail;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import scm.toolkit.Constants;

public class MailAgent {

	private String from;

	private String[] to;

	private String titleTemplateName;
	
	private String bodyTemplateName;
	
	private VelocityEngine ve = null;
	private VelocityContext context = null;

	private Session session = null;
	
	public MailAgent(Properties props) throws Exception {
		super();
		session = Session.getInstance(props, null);
		ve = new VelocityEngine();
		context = new VelocityContext();
		ve.init(props);
		
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String ... to) {
		this.to = to;
	}
	
	public String getTitleTemplateName() {
		return titleTemplateName;
	}

	public void setTitleTemplateName(String titleTemplateName) {
		this.titleTemplateName = titleTemplateName;
	}

	public String getBodyTemplateName() {
		return bodyTemplateName;
	}

	public void setBodyTemplateName(String bodyTemplate) {
		this.bodyTemplateName = bodyTemplate;
	}

	public void send(Object mailContent) throws ResourceNotFoundException, ParseErrorException, Exception {

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		
		List<InternetAddress> addrToList = new ArrayList<InternetAddress>();
		for (String addrTo : to) {
			addrToList.add(new InternetAddress(addrTo));
		}
		
		msg.setRecipients(Message.RecipientType.TO, addrToList.toArray(new InternetAddress[0]));
		
		msg.setSentDate(Calendar.getInstance().getTime());
		
		
		context.put("mailContent", mailContent);
		
		StringWriter writer = new StringWriter();
		Template template = null;
		
		template = ve.getTemplate(this.titleTemplateName, System.getProperty(Constants.FILE_ENCODING));
		template.merge(context, writer);
		
		msg.setSubject(writer.toString(), "UTF-8");
		
		writer = new StringWriter();
		template = ve.getTemplate(this.bodyTemplateName, System.getProperty(Constants.FILE_ENCODING));
		template.merge(context, writer);
		
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setText(writer.toString());
		
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp);
		
		msg.setContent(mp);
		
		Transport.send(msg);
	}
}
