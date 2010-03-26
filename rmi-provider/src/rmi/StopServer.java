package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StopServer {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		Registry registry = LocateRegistry.getRegistry();

		registry.unbind(RmiSample.SERVICE_NAME);
		
		System.out.println("rmi server stoped!!!");
	}
}
