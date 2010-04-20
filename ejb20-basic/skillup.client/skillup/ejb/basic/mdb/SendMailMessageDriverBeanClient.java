package skillup.ejb.basic.mdb;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SendMailMessageDriverBeanClient {

	public static void main(String[] args) throws NamingException, JMSException {

		InitialContext ict = new InitialContext();

		TopicConnectionFactory connectionFactory = (TopicConnectionFactory) ict.lookup("ExampleConnectionFactory");

		Topic topic = (Topic) ict.lookup("SendMailTopic");

		TopicConnection conn = connectionFactory.createTopicConnection();
		conn.start();

		TopicSession topicSession = conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);

		MessageProducer messageProducer = topicSession.createProducer(topic);

		ObjectMessage objMessage = topicSession.createObjectMessage();

		MailPojo mail = new MailPojo();
		mail.setFrom("javadb@gmail.com");
		mail.setTo("derby@gmail.com");
		mail.setSubject("JMSテスト");
		mail.setContent("こんにちは！");
		objMessage.setObject(mail);

		messageProducer.send(objMessage);

		messageProducer.close();

		topicSession.close();

		conn.stop();
		
		ict.close();
		
	}

}
