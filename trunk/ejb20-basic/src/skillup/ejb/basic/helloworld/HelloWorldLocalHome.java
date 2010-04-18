package skillup.ejb.basic.helloworld;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface HelloWorldLocalHome extends EJBLocalHome {
	
	public HelloWorldLocal create() throws CreateException;	
}
