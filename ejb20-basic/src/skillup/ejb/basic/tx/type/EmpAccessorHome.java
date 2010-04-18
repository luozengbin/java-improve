package skillup.ejb.basic.tx.type;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface EmpAccessorHome extends EJBHome {
	public EmpAccessor create() throws RemoteException, CreateException;
}
