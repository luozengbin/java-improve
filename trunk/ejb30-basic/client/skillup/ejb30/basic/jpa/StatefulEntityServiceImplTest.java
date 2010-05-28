package skillup.ejb30.basic.jpa;


import javax.transaction.UserTransaction;

import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class StatefulEntityServiceImplTest {
	
	EntityService service = ContextUtils.lookup("skillup_ejb30_basic/StatefulEntityServiceImpl/remote");

	@Test
	public void testCase001() throws Exception {
		
		
		UserTransaction tx = ContextUtils.lookup("UserTransaction");
		
		tx.begin();
		
		Entity001 e001 = new Entity001();
		
		e001.setName("nihao!!!");
		
		e001 = service.persistEntity(e001);
		
		//Thread.sleep(10 * 1000);
		
		e001.setName("akira");
		service.persistEntity(e001);
		
		tx.commit();
		
		System.out.println(service.getEntity(Entity001.class, e001.getId()));
		
	}
	
	@Test
	public void testCase002() throws Exception {
		
		
		UserTransaction tx = ContextUtils.lookup("UserTransaction");
		
		tx.begin();
		
		Entity001 e001 = service.getEntity(Entity001.class, 1);
		
		e001.setName("akira");
		service.persistEntity(e001);
		
		e001.setName("milan");
		service.persistEntity(e001);
		
		e001.setName("aaaaa");
		service.persistEntity(e001);
		
		tx.commit();
		
		System.out.println(service.getEntity(Entity001.class, e001.getId()));
		
	}
	
	@Test
	public void testCase003() throws Exception {
		
		
		UserTransaction tx = ContextUtils.lookup("UserTransaction");
		
		tx.begin();
		
		Entity001 e001 = service.getEntity(Entity001.class, 1);
		
		e001.setName("1111");
		service.persistEntity(e001);
		
		System.out.println("sleep!!!");
		Thread.sleep(20 * 1000);
		
		tx.commit();
		
		System.out.println(service.getEntity(Entity001.class, e001.getId()));
		
	}
	
	@Test
	public void testCase004() throws Exception {
		
		UserTransaction tx = ContextUtils.lookup("UserTransaction");
		
		tx.begin();
		
		Entity001 e001 = service.getEntity(Entity001.class, 1);
		
		System.out.println(e001);
		
		e001.setName("222");
		service.persistEntity(e001);
		
		tx.commit();
		
		System.out.println(service.getEntity(Entity001.class, e001.getId()));
		
	}
	
	@Test
	public void testCase005() throws Exception {
		
		Entity001 e001 = service.getEntity(Entity001.class, 1);
		
		System.out.println(e001);
		
		e001.setName("aaaa");
		service.persistEntity(e001);
		
		e001.setName("bbbb");
		service.persistEntity(e001);
		
		System.out.println(service.getEntity(Entity001.class, e001.getId()));
		
	}

}
