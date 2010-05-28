package skillup.ejb30.basic.jpa;


import org.junit.BeforeClass;
import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class ConversationServicImplTest {
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		Entity001 entity = new Entity001();
		entity.setName("akira");
		
		EntityService service = ContextUtils.lookup("skillup_ejb30_basic/EntityServiceImpl/remote");
		service.persistEntity(entity);
	}
	
	@Test
	public void testCase001() throws Exception {
		
		ConversationService service = ContextUtils.lookup("skillup_ejb30_basic/ConversationServicImpl/remote");
		
		Entity001 entity = service.getEntity(1);
		
		System.out.println(entity);
		
		service.updateName("milan");
		
		System.out.println("sleeping...");
		
		Thread.sleep(10 * 1000);
		
		service.commit();
		
	}

}
