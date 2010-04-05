package skillup.ejb.basic.statefull;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface AccessLogHome extends EJBHome {
	public AccessLog create() throws CreateException, RemoteException;
	public AccessLog createWithURL(String url) throws CreateException, RemoteException;
}
