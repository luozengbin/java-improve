package skillup.ejb.basic.statefull;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AccessLogBean implements SessionBean {
	
	private static final long serialVersionUID = -687015393438587378L;
	
	private static final Log log = LogFactory.getLog(AccessLogBean.class);
	
	private Collection<String> accessHistory = null;
	
	public void ejbCreate() {
		
		log.debug("ejbCreateted by container. hashcode(" + this.hashCode() + ")");
		
		accessHistory = new ArrayList<String>();
	}
	public void ejbCreateWithURL(String url) {
		
		log.debug("ejbCreateWithURL by container. hashcode(" + this.hashCode() + ")");
		
		accessHistory = new ArrayList<String>();
		accessHistory.add(url);
	}
	
	public Collection<String> getAccessHistory() throws RemoteException{
		
		log.debug("accessHistory:" + accessHistory + ". hashcode(" + this.hashCode() + ")");
		
		return accessHistory;
	}
	
	public void setLastURL(String url) throws RemoteException{
		
		log.debug("setLastURL(" + url + ")"+". hashcode(" + this.hashCode() + ")");
		
		accessHistory.add(url);
	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		log.debug("ejbActivate by container. hashcode(" + this.hashCode() + ")");
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		log.debug("ejbPassivate by container. hashcode(" + this.hashCode() + ")");
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		log.debug("ejbRemove by container. hashcode(" + this.hashCode() + ")");
	}

	@Override
	public void setSessionContext(SessionContext arg0) throws EJBException,RemoteException {
		log.debug("setSessionContext by container. hashcode(" + this.hashCode() + ")");
	}

}
