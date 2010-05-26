package skillup.ejb30.basic.jms;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import skillup.ejb30.basic.client.util.ContextUtils;

public class QueueSenderTest {
	
	public static void main(String[] args) throws JMSException {
		
		QueueConnectionFactory factory = null;
		QueueConnection conn = null;
		QueueSession session = null;
		Queue destination = null;
		MessageProducer producer = null;
		
		try {
			factory = ContextUtils.lookup("QueueConnectionFactory");
			conn = factory.createQueueConnection();
			conn.start();
			session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			destination = ContextUtils.lookup("queue/skillup");
			producer = session.createProducer(destination);
			
			TextMessage textMessage = session.createTextMessage("hello ejb3!!!");
			producer.send(textMessage);
			
			ObjectMessage objectMessage = session.createObjectMessage(new Man("akira", "china fujian"));
			producer.send(objectMessage);
			
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setString("name", "akira");
			mapMessage.setString("address", "china fujian");
			producer.send(mapMessage);
			
			BytesMessage bytesMessage = session.createBytesMessage();
			bytesMessage.writeBytes("akira from china fujian".getBytes());
			producer.send(bytesMessage);
			
			StreamMessage streamMessage = session.createStreamMessage();
			streamMessage.writeString("milan from china fujian too!!!");
			producer.send(streamMessage);
			
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
