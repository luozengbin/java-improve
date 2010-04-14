package skillup.ejb.basic.scott;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmpAccessorClient {
	
	private final static Log log = LogFactory.getLog(EmpAccessorClient.class);
	
	public static void main(String[] args) throws NamingException, RemoteException, CreateException, RemoveException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		Context ctx = new InitialContext();
		EmpAccessorHome home = (EmpAccessorHome) PortableRemoteObject.narrow(ctx.lookup("EmpAccessor"), EmpAccessorHome.class);
		
		javax.transaction.UserTransaction tx = (javax.transaction.UserTransaction)ctx.lookup("javax.transaction.UserTransaction");
		tx.begin();
		EmpAccessor empAccessor = home.create();
		empAccessor.updateSal("7934", 1300);
		tx.rollback();
		ctx.close();
	}
}
