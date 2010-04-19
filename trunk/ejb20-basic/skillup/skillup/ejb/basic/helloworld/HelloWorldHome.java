/**
 * 
 */
package skillup.ejb.basic.helloworld;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Homeインターフェース
 * 
 * Remoteインターフェース(HelloWorldE)を返すcreateメソッドを持つインターフェースです。
 * インターフェースEJBHomeを継承します。EJBHomeもインターフェースRemoteを継承してますので、
 * HomeインターフェースもRemoteインターフェースです。つまり、createメソッドはリモートから呼ばれます。
 * 
 * @author akira
 *
 */
public interface HelloWorldHome extends EJBHome {
	
	public HelloWorld create() throws RemoteException, CreateException;

}
