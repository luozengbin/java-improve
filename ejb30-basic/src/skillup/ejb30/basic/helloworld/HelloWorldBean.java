package skillup.ejb30.basic.helloworld;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(HelloWorld.class)
public class HelloWorldBean implements HelloWorld {

	@Override
	public String sayHello(String name) {
		return "Hello World " + name;
	}

}
