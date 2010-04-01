package skillup.ejb.basic.helloworld;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class HelloWorldClient {

	public static void main(String[] args) throws NamingException, RemoteException, CreateException {

		Context ctx = new InitialContext();

		HelloWorldHome home = (HelloWorldHome) PortableRemoteObject.narrow(ctx
				.lookup("HelloWorld"), HelloWorldHome.class);
		
		HelloWorld helloWorld = home.create();
		
		System.out.println(helloWorld.sayHelloWorld());
		
		ctx.close();

	}

}
