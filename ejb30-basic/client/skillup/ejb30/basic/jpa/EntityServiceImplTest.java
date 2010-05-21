package skillup.ejb30.basic.jpa;

import java.util.Calendar;

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
}
