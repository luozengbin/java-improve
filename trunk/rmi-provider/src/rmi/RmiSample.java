package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiSample extends Remote {
	
	static String SERVICE_NAME = "SampleService";
	
	public String getMessage() throws RemoteException;
	
}
