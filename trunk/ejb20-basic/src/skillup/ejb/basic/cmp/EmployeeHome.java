package skillup.ejb.basic.cmp;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface EmployeeHome extends EJBHome {

	public Employee create() throws RemoteException, CreateException;
}
