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
	
		/*
		 * LocateRegistry#createRegistry()で、 RMIレジストリーを作成（起動）する。
		 * Registry.REGISTRY_PORTは、RMIレジストリーのデフォルトのTCPポート番号（1099）。
		 * createすることで、TCP受付が始まる。
		 * （TCP受付をやめるには、作成したRMIレジストリーインスタンスをアンエクスポートする）
		 * このプログラムを実行すると、RMIレジストリーへのオブジェクトの登録後、無限ループに入る。
		 * （エクスポート（exportObject() 呼び出し）したオブジェクトは（RMIレジストリーに登録されている為、ではなく）管理テーブルで保持され、監視用スレッドが動く為。
		 */
		
		//RMIレジストリーへの登録前にそのインスタンスをUnicastRemoteObjectを使ってエクスポートしている。
		//リモートインターフェースの実装クラスがUnicastRemoteObjectを継承していれば、エクスポートの必要は無い。
		Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		
		//サーバーオブジェクトがUnicastRemoteObjectから継承していない場合、UnicastRemoteObject
		//のエクスポート機能を使わないとレジストリへの登録が不可です。
		//UnicastRemoteObject.exportObject(new RmiSampleServer())
		
		registry.rebind(RmiSample.SERVICE_NAME, new RmiSampleServer());
		
		System.out.println("rmi server started!!!");
		
		//自分で無限ループする必要は無い
	}

}
