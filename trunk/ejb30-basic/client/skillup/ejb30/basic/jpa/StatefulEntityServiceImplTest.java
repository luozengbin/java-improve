package skillup.ejb30.basic.jpa;


import java.util.List;

import javax.transaction.UserTransaction;

import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class StatefulEntityServiceImplTest {
	
	EntityService service = ContextUtils.lookup("skillup_ejb30_basic/StatefulEntityServiceImpl/remote");

	@Test
	public void testREQUIRES_NEW() throws Exception {
		
		
		UserTransaction tx = ContextUtils.lookup("UserTransaction");
		
		tx.begin();
		
		Entity001 e001 = new Entity001();
		
		e001.setName("nihao!!!");
		
		e001 = service.persistEntity(e001);
		
		tx.rollback();
		
		List<Entity001> entity001List =  service.getEntityList(Entity001.class);
		for (Entity001 entity001 : entity001List) {
			System.out.println(entity001);
		}
		
	}

}
