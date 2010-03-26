package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartServer {

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {

		Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

		registry.rebind(RmiSample.SERVICE_NAME, new RmiSampleServer());

		System.out.println("rmi server started!!!");
	}

}
