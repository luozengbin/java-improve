package skillup.ejb.basic.local;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloWorldInvokerHome extends EJBHome {
	
	public HelloWorldInvoker create() throws RemoteException, CreateException;
	
}
