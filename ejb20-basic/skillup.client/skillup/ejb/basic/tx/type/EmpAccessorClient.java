package skillup.ejb.basic.tx.type;

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
import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmpAccessorClient {
	
	private final static Log log = LogFactory.getLog(EmpAccessorClient.class);
	
	public static void main(String[] args) throws NamingException, RemoteException, CreateException, RemoveException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		Context ctx = new InitialContext();
		EmpAccessorHome home = (EmpAccessorHome) PortableRemoteObject.narrow(ctx.lookup("EmpAccessor"), EmpAccessorHome.class);
		
		UserTransaction tx = (UserTransaction)ctx.lookup("javax.transaction.UserTransaction");
		tx.begin();
		EmpAccessor empAccessor = home.create();
		
		/* test 001*/
		//empAccessor.updateNerver("7934", 1300);
		
		/* test 002*/
		//empAccessor.updateRequiresNew("7934", 5000);
		//empAccessor.updateRequired("7934", 1305);
		
		/* test 003*/
		//empAccessor.updateNotSupported("7934", 3000);
		//empAccessor.updateSupports("7934", 3500);
		
		/* test 004*/
		empAccessor.updateMandatory("7934", 8000);
		
		tx.rollback();
		ctx.close();
	}
}
