package skillup.ejb.basic.scott;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface EmpAccessor extends EJBObject {
	
	public void updateSal(String empNo, double newSal) throws RemoteException;
	
	public void updateDept(String empNo, String newDeptNo) throws RemoteException;

}
