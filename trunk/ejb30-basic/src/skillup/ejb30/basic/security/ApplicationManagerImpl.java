package skillup.ejb30.basic.security;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@Remote
@SecurityDomain(unauthenticatedPrincipal="anonymous",value="ejb3-skillup")
public class ApplicationManagerImpl implements ApplicationManager {
	
	static final Log log = LogFactory.getLog(ApplicationManagerImpl.class);
	
	@Resource private SessionContext ctx;
	
	@PostConstruct
	public void post(){
		
	}
	
	@Override
	@RolesAllowed({"admin"})
	public void createApplication(String applicationName) {
		log.info("createApplication [" + applicationName + "] by " + ctx.getCallerPrincipal().getName());
	}

	@Override
	@RolesAllowed({"admin"})
	public void deleteApplication(String applicationName) {
		log.info("deleteApplication [" + applicationName + "] by " + ctx.getCallerPrincipal().getName());
	}

	@Override
	@RolesAllowed({"cunsumer", "developer", "admin"})
	public void feedback(String applicationName, String comment) {
		log.info("feedback for [" + applicationName + "]:{" + comment + "} by " + ctx.getCallerPrincipal().getName());
	}

	@Override
	@RolesAllowed({"cunsumer", "developer", "admin"})
	public void runApplication(String applicationName) {
		log.info("runApplication [" + applicationName + "] by " + ctx.getCallerPrincipal().getName());
	}

	@Override
	@RolesAllowed({"developer", "admin"})
	public void updateApplication(String applicationName,String version) {
		log.info("updateApplication [" + applicationName + "] to new version [" + version + "] by " + ctx.getCallerPrincipal().getName());
	}

	@Override
	@PermitAll
	public String checkApplicationInfo(String applicationName) {
		return "sorry!!!" + ctx.getCallerPrincipal().getName() + " this service is not public yet";
	}

}
