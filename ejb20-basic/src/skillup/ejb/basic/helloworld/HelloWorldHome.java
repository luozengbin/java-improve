package skillup.ejb.basic.helloworld;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloWorldHome extends EJBHome {
	HelloWorld create() throws CreateException, RemoteException;
}
