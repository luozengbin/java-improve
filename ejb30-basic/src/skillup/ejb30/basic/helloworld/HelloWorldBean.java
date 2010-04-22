package skillup.ejb30.basic.helloworld;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import skillup.ejb30.basic.other.Other;

@Stateless
@Remote({HelloWorld.class, HelloWorldRemote.class})
@Local(HelloWorldLocal.class)
public class HelloWorldBean implements HelloWorld, HelloWorldRemote ,HelloWorldLocal {
	
	@EJB
	Other other;

	@Override
	public String sayHello(String name) {
		return "Hello World " + name + " --> " + other.sayMe();
	}

}
