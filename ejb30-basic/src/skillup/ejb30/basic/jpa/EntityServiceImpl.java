package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
@Remote(EntityService.class)
public class EntityServiceImpl implements EntityService {

	static final Log log = LogFactory.getLog(EntityServiceImpl.class);
	
	@PersistenceContext(unitName = "ejb30-basic")
	private EntityManager em;
	
	public void persistEntity(Object... entitys) {
		for (Object object : entitys) {
			em.persist(object);
		}
	}
	
	public <T> T getEntity(Class<T> clzss, Object key) {
		return em.find(clzss, key);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getEntityList(Class<T> clzss) {
		return em.createQuery("SELECT o FROM " + clzss.getSimpleName() + " o").getResultList();
	}
	
	@Remove
	@Override
	public void sync() {
		log.info("executing sync");
	}
}
