package skillup.ejb30.basic.injection;


import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class InjectionFacadeTest {

	@Test
	public void testSayHello() throws Exception {
		
		Injection bean = ContextUtils.lookup("skillup_ejb30_basic/InjectionFacade/remote");
		
		System.out.println(bean.sayHello("akira"));
	}
	
}
