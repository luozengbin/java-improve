package skillup.ejb.basic.helloworld;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class HelloWorldBean implements SessionBean {

	private static final long serialVersionUID = 7214781896345620375L;

	public String sayHelloWorld() {
		return "Hello World!!!";
	}

	public void ejbCreate() throws CreateException {
		System.out.println("---->ejbCreate");
	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		System.out.println("---->ejbActivate");
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		System.out.println("---->ejbPassivate");
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		System.out.println("---->ejbRemove");
	}

	@Override
	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		System.out.println("---->setSessionContext");
	}

}
