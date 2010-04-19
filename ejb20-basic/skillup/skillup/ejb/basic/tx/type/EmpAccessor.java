package skillup.ejb.basic.tx.type;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface EmpAccessor extends EJBObject {
	
	public void updateRequired(String empNo, double newSal) throws RemoteException;
	
	public void updateRequiresNew(String empNo, double newSal) throws RemoteException;
	
	public void updateSupports(String empNo, double newSal) throws RemoteException;
	
	public void updateNotSupported(String empNo, double newSal) throws RemoteException;
	
	public void updateNerver(String empNo, double newSal) throws RemoteException;
	
	public void updateMandatory(String empNo, double newSal) throws RemoteException;
}
