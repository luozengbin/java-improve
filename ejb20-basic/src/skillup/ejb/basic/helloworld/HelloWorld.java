package skillup.ejb.basic.helloworld;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface HelloWorld extends EJBObject {
	public String sayHelloWorld() throws RemoteException;
}
