/**
 * 
 */
package skillup.ejb.basic.helloworld;


import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Beanクラスにはリモートメソッドの中身を含めて記述します。
 * @author akira
 */
public class HelloWorldBean implements SessionBean {

	/**
	 * Serialize で使うバージョンID
	 */
	private static final long serialVersionUID = -7559283790118864024L;
	
	public HelloWorldBean() {
		super();
	}
	
	/**
	 * 業務ロジックの実装
	 * @return
	 */
	public String sayHelloWorld() {
		return "Hello EJB World!!!";
	}
	
	/**
	 * クライアントがcreateメソッドを呼び出したときコンテナによって呼び出されます。
	 */
	public void ejbCreate() {System.out.println("--->ejbCreate");}
	
	/**
	 * セッションBeanのインスタンス生成後、コンテナによって呼び出され、セッションBeanと、コンテナが保有するコンテキストとの関連付けを行う。
	 */
	public void setSessionContext(SessionContext paramSessionContext) throws EJBException, RemoteException { System.out.println("--->setSessionContext"); }
	
	/**
	 *非活性状態→活性状態になるときに呼び出されます。このメソッドでは ejbPassvate()メソッドで開放したリソースを取得しなければなりません。
	 */
	public void ejbActivate() throws EJBException, RemoteException {System.out.println("--->ejbActivate");}

	/**
	 * 活性状態→非活性状態になるときに呼び出されます。リソースの開放を行います。
	 */
	public void ejbPassivate() throws EJBException, RemoteException {System.out.println("--->ejbPassivate");}

	
	/**
	 * クライアントがremoveメソッドを呼び出した場合、またはセッションのタイムアウトの場合に、セッションオブジェクトが消滅する前にコンテナによって呼びだれます。
	 */
	public void ejbRemove() throws EJBException, RemoteException {System.out.println("--->ejbRemove");}
}
