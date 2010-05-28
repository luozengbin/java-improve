package skillup.ejb30.basic.jpa;

import java.util.Calendar;
import java.util.List;

import javax.transaction.UserTransaction;

import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class EntityServiceImplTest {
	
	static EntityService service = ContextUtils.lookup("skillup_ejb30_basic/EntityServiceImpl/remote");
	

	@Test
	public void testPersistEntity() {
		
		Publish publishInfo = new Publish();
		publishInfo.setPublishDt(Calendar.getInstance().getTime());
		publishInfo.setPublisherId("6");
		
		News news = new News();
		news.setTitle("this a big news!!!");
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 10; i++) {
			sb.append("message from ejb3 line:" + i + "\n");
		}
		news.setContent(sb.toString());
		news.setPublishInfo(publishInfo);
		service.persistEntity(news);
	}
	
	@Test
	public void testname() throws Exception {
		List<News> newsList = service.getEntityList(News.class);
		
		for (News news : newsList) {
			System.out.println(news);
		}
	}
	
	@Test
	public void testEntity001() throws Exception {
		
		UserTransaction tx = ContextUtils.lookup("UserTransaction");
		
		tx.begin();
		
		Entity001 e001 = new Entity001();
		
		e001.setName("nihao!!!");
		
		e001 = service.persistEntity(e001);
		
		tx.commit();
		
		//tx.rollback();
		
		//service.remove(e001, e001);
		
		System.out.println(e001);
	}
	
	@Test
	public void testGetEntity001List() throws Exception {
		
		service = ContextUtils.lookup("skillup_ejb30_basic/TempEntityServiceImpl/remote");
		
		List<Entity001> entity001List =  service.getEntityList(Entity001.class);
		for (Entity001 entity001 : entity001List) {
			System.out.println(entity001);
			//service.remove(entity001);
		}
	}
	
	@Test
	public void testGetEntity001List001() throws Exception {
		service = ContextUtils.lookup("skillup_ejb30_basic/TempEntityServiceImpl/remote");
		System.out.println(service.getEntity(Entity001.class, 1));
	}
	
	@Test
	public void testFlushMode() throws Exception {
		
		
		UserTransaction tx = ContextUtils.lookup("UserTransaction");
		
		tx.begin();
		
		Entity001 e001 = new Entity001();
		
		e001.setName("nihao!!!");
		
		Entity001 e002 = new Entity001();
		
		e002.setName("akira!!!");
		
		service.persistEntity(e001, e002);
		
		service = ContextUtils.lookup("skillup_ejb30_basic/TempEntityServiceImpl/remote");
		System.out.println(service.getEntity(Entity001.class, 1));
		
		System.out.println("sleeping...");
		
		Thread.sleep(20 * 1000);
		
		service.persistEntity(e001, e002);
		
		tx.commit();
		
		System.out.println("OK!!!!!");
	}
}
