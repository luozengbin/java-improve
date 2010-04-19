package skillup.ejb.basic.cmp;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class EmployeeEitity implements EntityBean {

	private static final Log log = LogFactory.getLog(EmployeeEitity.class);

	private static final long serialVersionUID = -5117101840059426957L;

	public abstract String getEmpNo(); // primary key

	public abstract void setEmpNo(String empNo);

	public abstract String getName();

	public abstract void setName(String Name);

	public abstract String getJob();

	public abstract void setJob(String job);

	public abstract String getMgr();

	public abstract void setMgr(String mgr);

	public abstract Date getHireDate();

	public abstract void setHireDate(Date hireDate);

	public abstract double getSal();

	public abstract void setSal(double sal);

	public abstract String getComm();

	public abstract void setComm(String comm);

	public abstract String getDeptNo();

	public abstract void setDeptNo(String deptNo);

	public String ejbCreate(String empNo, String name) throws CreateException {
		log.debug("ejbCreate...");
		return create(empNo, name);
	}
	
	/* キー項目と必須項目を初期化しないと行けない */
	private String create(String empNo, String name) throws CreateException {

		log.debug("create...");

		setEmpNo(empNo);
		setName(name);

		return empNo;
	}

	public void ejbPostCreate(String empNo, String name)
			throws CreateException {
		
		log.debug("ejbPostCreate...");
	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		log.debug("ejbActivate...");
	}

	@Override
	public void ejbLoad() throws EJBException, RemoteException {
		log.debug("ejbLoad...");
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		log.debug("ejbPassivate...");
	}

	@Override
	public void ejbRemove() throws RemoveException, EJBException,
			RemoteException {
		log.debug("ejbRemove...");
	}

	@Override
	public void ejbStore() throws EJBException, RemoteException {
		log.debug("ejbStore (when tx commited)...");
	}

	@Override
	public void setEntityContext(EntityContext arg0) throws EJBException,
			RemoteException {
		log.debug("setEntityContext...");
	}

	@Override
	public void unsetEntityContext() throws EJBException, RemoteException {
		log.debug("unsetEntityContext...");
	}

}
