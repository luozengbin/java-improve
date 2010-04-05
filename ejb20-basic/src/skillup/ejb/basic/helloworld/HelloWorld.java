package skillup.ejb.basic.helloworld;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;


/**
 * ■Remoteインターフェース
 * RemoteインターフェースはRemoteを継承しなければなりません。
 * インターフェースEJBObjectがRemoteを継承しています。
 * Remoteインターフェースにはリモートメソッドの定義を記述します。
 * 
 * @author akira
 */
public interface HelloWorld extends EJBObject {

	public String sayHelloWorld() throws RemoteException;
	
}
