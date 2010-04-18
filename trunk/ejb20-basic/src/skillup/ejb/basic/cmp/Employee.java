package skillup.ejb.basic.cmp;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Employee extends EJBObject {
	
	public void updateSal(String empNo, double newSal) throws RemoteException;
}
