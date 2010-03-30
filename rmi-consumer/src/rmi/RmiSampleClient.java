package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiSampleClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		//java.rmi.Naming#lookup()で、RMIレジストリー（デフォルトでポート1099）からリモートオブジェクトのインターフェースを取得する。
		RmiSample rmiSample = (RmiSample)Naming.lookup(RmiSample.SERVICE_NAME);
		
		//もしくはRegistryを使って取得する。（というか、Namingの内部はRegistryを使っている模様）
		/*
		Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
		Remote r = registry.lookup("RmiSampleName");
		*/
		
		System.out.println(rmiSample.getMessage());
		
	}
	
}
