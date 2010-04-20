package skillup.ejb30.basic.helloworld;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloWorldBeanClient {

	public static void main(String[] args) throws NamingException {
		
		InitialContext itx = new InitialContext();
		
		HelloWorld helloWorld = (HelloWorld)itx.lookup("HelloWorldBean/remote");
		
		System.out.println(helloWorld.sayHello("akira"));
		
		itx.close();
	}
}
