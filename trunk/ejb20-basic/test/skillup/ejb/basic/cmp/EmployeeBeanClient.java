package skillup.ejb.basic.cmp;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class EmployeeBeanClient {

	public static void main(String[] args) throws NamingException,
			RemoteException, CreateException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

		InitialContext ctx = new InitialContext();

		EmployeeHome home = (EmployeeHome) PortableRemoteObject.narrow(ctx.lookup("EmployeeBean"), EmployeeHome.class);

		UserTransaction tx = (UserTransaction) ctx.lookup("javax.transaction.UserTransaction");
		tx.begin();

		Employee emp = home.create();

		emp.updateSal("8888", 6000);

		tx.commit();

		ctx.close();

	}
}
