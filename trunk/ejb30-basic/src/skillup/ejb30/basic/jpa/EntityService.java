package skillup.ejb30.basic.jpa;

import java.util.List;

public interface EntityService {
	
	public void persistEntity(Object... entitys);
	
	public <T> T getEntity(Class<T> clzss, Object key);

	public <T> List<T> getEntityList(Class<T> clzss);
	
	public void sync();
}
