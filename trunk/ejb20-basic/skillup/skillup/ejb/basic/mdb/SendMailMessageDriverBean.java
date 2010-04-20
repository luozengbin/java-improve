package skillup.ejb.basic.mdb;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SendMailMessageDriverBean implements MessageDrivenBean, MessageListener {
	
	private static final Log log = LogFactory.getLog(SendMailMessageDriverBean.class);
	
	private static final long serialVersionUID = 5750719139566546143L;
	
	public void ejbCreate() {
		log.debug("ejbCreate...");
	}
	
	@Override
	public void ejbRemove() throws EJBException {
		log.debug("ejbRemove...");
	}

	@Override
	public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext)
			throws EJBException {
		
		log.debug("setMessageDrivenContext...");
		
	}

	@Override
	public void onMessage(Message message) {
		log.debug("onMessage:" + message);
		
		if(message instanceof ObjectMessage){
			try {
				MailPojo mailObj = (MailPojo)((ObjectMessage)message).getObject();
				
				log.debug(mailObj);
				
			} catch (JMSException e) {
				log.error(e);
			}
			
		}else{
			log.warn("message type is wrong!!!");
		}
	}

}
