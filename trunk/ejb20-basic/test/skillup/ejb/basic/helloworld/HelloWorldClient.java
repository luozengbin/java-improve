package skillup.ejb.basic.helloworld;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloWorldClient {

	private final static Log log = LogFactory.getLog(HelloWorldClient.class);

	public static void main(String[] args) throws NamingException,
			RemoteException, CreateException, RemoveException, InterruptedException {
		
		
		/* TestCase 001
		Context ctx = new InitialContext();
		HelloWorldHome home = (HelloWorldHome) PortableRemoteObject.narrow(ctx.lookup("HelloWorld"), HelloWorldHome.class);
		ctx.close();

		HelloWorld helloWorld_1 = home.create();
		HelloWorld helloWorld_2 = home.create();

		if (helloWorld_1.isIdentical(helloWorld_2)) {
			log.debug("helloWorld_1 == helloWorld_2");
		}

		log.debug("helloWorld_1.sayHelloWorld() :" + helloWorld_1.sayHelloWorld());
		log.debug("helloWorld_2.sayHelloWorld() :"+ helloWorld_2.sayHelloWorld());

		log.debug("isStatelessSession :" + home.getEJBMetaData().isStatelessSession());

		Collection<HelloWorld> ejbList = new ArrayList<HelloWorld>();

		HelloWorld ejbObj = null;
		for (int i = 0; i < 10; i++) {
			ejbObj = home.create();
			ejbList.add(ejbObj);
			log.debug(i + ":" + ejbObj.sayHelloWorld() + " same instance :"+ ejbObj.isIdentical(helloWorld_1));
		}*/

		
		class EJBInvoker extends Thread {
			
			private HelloWorld helloWorld = null;
			
			public HelloWorld getHelloWorld() {
				return helloWorld;
			}

			public void setHelloWorld(HelloWorld helloWorld) {
				this.helloWorld = helloWorld;
			}
			
			public void run() { 
				Context ctx;
				try {
					ctx = new InitialContext();
					HelloWorldHome home = (HelloWorldHome)PortableRemoteObject.narrow(ctx.lookup("HelloWorld"),HelloWorldHome.class); 
					ctx.close();
					helloWorld = home.create();
					log.debug(helloWorld.sayHelloWorld());
					sleep(60000);
					
				} catch (NamingException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (CreateException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			} 
		};
		
		
		EJBInvoker invoker_1 = new EJBInvoker();
		EJBInvoker invoker_2 = new EJBInvoker();
		EJBInvoker invoker_3 = new EJBInvoker();
		invoker_1.start();
		invoker_2.start();
		invoker_3.start();
		
		Thread.sleep(2000);
		
		
		//サーバー側３つインスタンスが生成されるけど、EJBの世界ではstatelessなEJBObjectが同じものと見直されています。
		log.debug("isIdentical: " + invoker_1.getHelloWorld().isIdentical(invoker_2.getHelloWorld()));
		log.debug("isIdentical: " + invoker_1.getHelloWorld().isIdentical(invoker_3.getHelloWorld()));
		
		//次bean-cacheの設定可能？
	}
}
