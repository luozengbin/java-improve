package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StopServer {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		
//		Registry registry = LocateRegistry.getRegistry();
//		
//		UnicastRemoteObject.unexportObject(registry, true);
		
		System.out.println("rmi server stoped!!!");
	}
}
