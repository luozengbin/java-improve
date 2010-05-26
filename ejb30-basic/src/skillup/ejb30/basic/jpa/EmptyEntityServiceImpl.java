package skillup.ejb30.basic.jpa;

import java.util.List;

public class EmptyEntityServiceImpl implements EntityService {

	@Override
	public <T> T getEntity(Class<T> clzss, Object key) {
		return null;
	}

	@Override
	public <T> List<T> getEntityList(Class<T> clzss) {
		return null;
	}

	@Override
	public void persistEntity(Object... entitys) {

	}

	@Override
	public <T> T persistEntity(T entity) {
		return null;
	}


	@Override
	public void sync() {

	}

	@Override
	public <T> void remove(T entity, Object... ids) {
		
	}

	@Override
	public void remove(Object entity) {
		
	}

}
