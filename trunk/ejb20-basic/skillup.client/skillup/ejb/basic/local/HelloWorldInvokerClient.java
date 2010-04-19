package skillup.ejb.basic.local;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloWorldInvokerClient {
	
	private final static Log log = LogFactory.getLog(HelloWorldInvokerClient.class);
	
	public static void main(String[] args) throws ClassCastException, NamingException, RemoteException, CreateException {
		
		Context ctx = new InitialContext();
		HelloWorldInvokerHome home = (HelloWorldInvokerHome) PortableRemoteObject.narrow(ctx.lookup("HelloWorldInvoker"), HelloWorldInvokerHome.class);
		ctx.close();
		
		log.debug(home.create().call());
	}
}
