package skillup.ejb.basic.local;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import skillup.ejb.basic.helloworld.HelloWorldLocal;
import skillup.ejb.basic.helloworld.HelloWorldLocalHome;

public class HelloWorldInvokerBean implements SessionBean {

	private static final long serialVersionUID = 7125722170936280230L;
	
	private static final Log log = LogFactory.getLog(HelloWorldInvokerBean.class);

	public void ejbCreate() {
		log.debug("ejbCreate...");

	}
	
	public String call() throws NamingException, CreateException{
		InitialContext itx = new InitialContext();
		HelloWorldLocalHome localHome = (HelloWorldLocalHome)itx.lookup("HelloWorldLocal");
		
		HelloWorldLocal local = localHome.create();
		
		log.debug("local bean :" + local.hashCode());
		
		return local.sayHelloWorld();
	}
	
	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		log.debug("ejbActivate...");
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		log.debug("ejbPassivate...");
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		log.debug("ejbRemove...");
	}

	@Override
	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
		log.debug("setSessionContext...");
	}

}
