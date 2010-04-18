package skillup.ejb.basic.helloworld;

import javax.ejb.EJBLocalObject;

public interface HelloWorldLocal extends EJBLocalObject {
	public String sayHelloWorld();
}
