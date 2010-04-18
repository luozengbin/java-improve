package skillup.ejb.basic.local;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface HelloWorldInvoker  extends EJBObject {
	
	public String call() throws RemoteException;
	
}
