package skillup.ejb30.basic.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import skillup.ejb30.basic.client.util.ContextUtils;

public class TopicSenderTest {
	
	public static void main(String[] args) throws JMSException {
		
		TopicConnectionFactory factory = null;
		TopicConnection conn = null;
		TopicSession session = null;
		Topic destination = null;
		MessageProducer producer = null;
		
		try {
			factory = ContextUtils.lookup("TopicConnectionFactory");
			conn = factory.createTopicConnection();
			conn.start();
			session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
			destination = ContextUtils.lookup("topic/skillup");
			producer = session.createProducer(destination);
			
			TextMessage textMessage = session.createTextMessage("hello ejb3!!!");
			producer.send(textMessage);
			
			ObjectMessage objectMessage = session.createObjectMessage(new Man("akira", "china fujian"));
			producer.send(objectMessage);
			
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setString("name", "akira");
			mapMessage.setString("address", "china fujian");
			producer.send(mapMessage);
			
//			BytesMessage bytesMessage = session.createBytesMessage();
//			bytesMessage.writeBytes("akira from china fujian".getBytes());
//			producer.send(bytesMessage);
//			
//			StreamMessage streamMessage = session.createStreamMessage();
//			streamMessage.writeString("milan from china fujian too!!!");
//			producer.send(streamMessage);
			
		} catch (Exception e) {
			
			if(session != null) session.close();
			if(conn != null) conn.close();
			
			factory = null;
			conn = null;
			session = null;
			destination = null;
			producer = null;
		}
	}
}
