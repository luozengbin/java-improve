package skillup.ejb30.basic.helloworld;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import skillup.ejb30.basic.other.Other;

@Stateless
@Remote({HelloWorld.class, HelloWorldRemote.class}) //ここに複数IFを公開することが可能です
@Local(HelloWorldLocal.class)
public class HelloWorldBean /*implements HelloWorld, HelloWorldRemote ,HelloWorldLocal*/ {
	
	@EJB //別EJBを引用する
	Other other;

	//@Override
	public String sayHello(String name) {
		return "Hello World " + name + " --> " + other.sayMe();
	}

}
