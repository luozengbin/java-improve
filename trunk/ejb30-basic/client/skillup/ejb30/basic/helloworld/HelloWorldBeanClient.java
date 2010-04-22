package skillup.ejb30.basic.helloworld;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloWorldBeanClient {

	public static void main(String[] args) throws NamingException {
		
		InitialContext itx = new InitialContext();
		
		HelloWorld helloWorld = (HelloWorld)itx.lookup("skillup_ejb30_basic/HelloWorldBean/remote");
		
		//HelloWorldRemote helloWorld = (HelloWorldRemote)itx.lookup("skillup_ejb30_basic/HelloWorldBean/remote-skillup.ejb30.basic.helloworld.HelloWorldRemote");
		
		//HelloWorld helloWorld = (HelloWorld)itx.lookup("skillup_ejb30_basic/HelloWorldBean/remote-skillup.ejb30.basic.helloworld.HelloWorld");
		
		System.out.println(helloWorld.sayHello("akira"));
		
		System.out.println(helloWorld.getClass().getName());
		
		itx.close();
	}
}
