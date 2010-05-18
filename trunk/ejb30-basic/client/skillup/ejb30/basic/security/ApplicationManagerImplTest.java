package skillup.ejb30.basic.security;

import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;
import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class ApplicationManagerImplTest {

	@Test
	public void testApplicationManagerImpl001() throws Exception {

		ApplicationManager bean = ContextUtils.lookup("skillup_ejb30_basic/ApplicationManagerImpl/remote");

		SecurityClient securityClient = SecurityClientFactory.getSecurityClient();
		securityClient.setSimple("akira", "milan");
		securityClient.login();

		bean.createApplication("iphone");

		bean.updateApplication("iphone", "4.0");

		System.out.println(bean.checkApplicationInfo("iphone os"));

		bean.runApplication("iphone");

		bean.feedback("iphone", "it's smart!!!");

		bean.deleteApplication("iphone");

		securityClient.logout();
	}

	@Test
	public void testApplicationManagerImpl002() {

		try {

			ApplicationManager bean = ContextUtils.lookup("skillup_ejb30_basic/ApplicationManagerImpl/remote");

			SecurityClient securityClient = SecurityClientFactory.getSecurityClient();
			securityClient.setSimple("milan", "akira");
			securityClient.login();

			System.out.println(bean.checkApplicationInfo("iphone os"));

			bean.runApplication("iphone");

			bean.feedback("iphone", "it's smart!!!");

			securityClient.logout();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
