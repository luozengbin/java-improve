package skillup.ejb30.basic.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@MessageDriven(activationConfig={
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue")
		,@ActivationConfigProperty(propertyName="destination", propertyValue="queue/skillup")
		,@ActivationConfigProperty(propertyName="acknowledgeMode", propertyValue="Auto-acknowledge")	}
)
public class QueuePrintBean implements MessageListener {
	
	static final Log log = LogFactory.getLog(QueuePrintBean.class);
	
	@Override
	public void onMessage(Message msg) {
		
		try {
			if(msg instanceof TextMessage){
				log.info(((TextMessage)msg).getText());
			} else if(msg instanceof ObjectMessage){
				ObjectMessage objectMessage = (ObjectMessage)msg;
				log.info(objectMessage.getObject());
			} else if(msg instanceof MapMessage){
				MapMessage mapMessage = (MapMessage)msg;
				log.info("name:" + mapMessage.getString("name"));
				log.info("address:" + mapMessage.getString("address"));
			} else if(msg instanceof BytesMessage){
				BytesMessage bytesMessage = (BytesMessage)msg;
				log.info(bytesMessage.toString());
			} else if(msg instanceof StreamMessage){
				StreamMessage streamMessage = (StreamMessage)msg;
				log.info(streamMessage.readString());
			}
		} catch (JMSException e) {
			e.printStackTrace();
			log.error(e);
		}
	}

}
