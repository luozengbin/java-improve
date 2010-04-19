package skillup.ejb.basic.cmp;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmployeeBean implements SessionBean {

	private static final long serialVersionUID = -1538728690827296072L;
	
	private static final Log log = LogFactory.getLog(EmployeeBean.class);
	
	public void updateSal(String empNo, double newSal) throws NamingException, CreateException {
		
		InitialContext itx = new InitialContext();
		
		LocalEmployeeHome home = (LocalEmployeeHome)itx.lookup("EmployeeEitityLocal");
		
		LocalEmployee employee = null;
		
		try {
			employee = home.findByPrimaryKey(empNo);
		} catch (FinderException e) {
			log.error(e);
		}
		
		if(employee == null){
			
			employee = home.create(empNo, "demo");
			
			employee.setSal(newSal);
			
			log.debug("demo's sal:" + employee.getSal());
			
		} else {
			log.debug(employee.getName() + "'s old sal:" + employee.getSal());
			employee.setSal(newSal);
		}
	}
	
	public void ejbCreate() {
		log.debug("ejbCreate...");
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
		log.debug("setSessionContext...");
	}

}
