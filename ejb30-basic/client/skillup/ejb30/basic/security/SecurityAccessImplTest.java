package skillup.ejb30.basic.security;

import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;
import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;


public class SecurityAccessImplTest {
	
	@Test
	public void testSecurityAccessImpl() throws Exception {
		
		//SecurityClient securityClient = SecurityClientFactory.getSecurityClient();
		
		//securityClient.setSimple("milan", "akira");
		//securityClient.login();
		
		
		
		SecurityAccess bean = ContextUtils.lookup("skillup_ejb30_basic/SecurityAccessImpl/remote", "milan", "akira");
		
		//bean.updateApplication("iphone os", "4.0");
		
		//bean.runApplication("iphone os");
		
		System.out.println(bean.checkApplicationInfo("iphone os"));
		
	}

}
