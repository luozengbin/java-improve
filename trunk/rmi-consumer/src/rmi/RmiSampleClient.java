package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiSampleClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		RmiSample rmiSample = (RmiSample)Naming.lookup(RmiSample.SERVICE_NAME);
		System.out.println(rmiSample.getMessage());
		
	}
	
}
