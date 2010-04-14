package skillup.ejb.basic.scott;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmpAccessorBean implements SessionBean {

	private static final long serialVersionUID = -6299849763758991122L;

	private static final Log log = LogFactory.getLog(EmpAccessorBean.class);

	public void ejbCreate() {
		log.debug("ejbCreate...");

	}

	public void updateSal(String empNo, double newSal) throws NamingException, SQLException {
		log.debug("updateSal...");
		
		javax.naming.Context ctx = new javax.naming.InitialContext();
		
		DataSource ds = (DataSource)ctx.lookup("db/denver/scott");
		
		Connection conn = ds.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement("UPDATE EMP SET SAL = ? WHERE EMPNO = ?");
		stmt.setDouble(1, newSal);
		stmt.setString(2, empNo);
		stmt.executeUpdate();
		stmt.close();
		conn.close();
		
	}

	public void updateDept(String empNo, String newDeptNo) {
		log.debug("updateDept...");
	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		log.debug("ejbActivate...");
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		log.debug("ejbPassivate...");
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		log.debug("ejbRemove...");
	}

	@Override
	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		log.debug("SessionContext...");
	}

}
