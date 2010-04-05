/**
 * 
 */
package skillup.ejb.basic.helloworld;


import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Beanクラスにはリモートメソッドの中身を含めて記述します。
 * @author akira
 */
public class HelloWorldBean implements SessionBean {
	
	private static final Log log = LogFactory.getLog(HelloWorldBean.class);

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
		
		log.debug("sayHelloWorld invoke by client! hashCode = " + this.hashCode());
		
		try {
			log.debug("sleep 10 seconds!!!! ");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "Hello EJB World!!!";
	}
	
	/**
	 * クライアントがcreateメソッドを呼び出したときコンテナによって呼び出されます。
	 */
	public void ejbCreate() {
		log.debug("ejbCreate executed by container");
	}
	
	/**
	 * セッションBeanのインスタンス生成後、コンテナによって呼び出され、セッションBeanと、コンテナが保有するコンテキストとの関連付けを行う。
	 */
	public void setSessionContext(SessionContext paramSessionContext) throws EJBException, RemoteException { 
		log.debug("setSessionContext executed by container");
	}
	
	/**
	 *非活性状態→活性状態になるときに呼び出されます。このメソッドでは ejbPassvate()メソッドで開放したリソースを取得しなければなりません。
	 */
	public void ejbActivate() throws EJBException, RemoteException {
		log.debug("ejbActivate executed by container");
	}

	/**
	 * 活性状態→非活性状態になるときに呼び出されます。リソースの開放を行います。
	 */
	public void ejbPassivate() throws EJBException, RemoteException {
		log.debug("ejbPassivate executed by container");
	}

	
	/**
	 * クライアントがremoveメソッドを呼び出した場合、またはセッションのタイムアウトの場合に、セッションオブジェクトが消滅する前にコンテナによって呼びだれます。
	 */
	public void ejbRemove() throws EJBException, RemoteException {
		log.debug("ejbRemove executed by container");
	}
}
