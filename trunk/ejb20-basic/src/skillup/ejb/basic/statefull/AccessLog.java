package skillup.ejb.basic.statefull;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBObject;

public interface AccessLog extends EJBObject {
	
	public Collection<String> getAccessHistory() throws RemoteException;
	
	public void setLastURL(String url) throws RemoteException;

}
