package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
@Remote(EntityService.class)
public class EntityServiceImpl extends EmptyEntityServiceImpl {

	static final Log log = LogFactory.getLog(EntityServiceImpl.class);
	
	@PersistenceContext(unitName = "ejb30-basic")
	private EntityManager em;
	
	public <T> T persistEntity(T entity) {
		em.persist(entity);
		return entity;
	}
	
	public void persistEntity(Object ... entitys) {
		
//		em.setFlushMode(FlushModeType.AUTO);
		
		//em.setFlushMode(FlushModeType.COMMIT);
		
		log.info("hashcode:" + this.hashCode());
		
		for (Object object : entitys) {
			em.persist(object);
		}
		
		em.flush();
		
		List list= getEntityList(entitys[0].getClass());
		log.info("before commit:" + list.size());
		
//		try {
//			Thread.sleep(20*1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
	}
	
	public <T> T getEntity(Class<T> clzss, Object key) {
		log.info("hashcode:" + this.hashCode());
		return em.find(clzss, key);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getEntityList(Class<T> clzss) {
		log.info("hashcode:" + this.hashCode());
		
		return em.createQuery("SELECT o FROM Entity001 o where o.id = 3").getResultList();
		
		//return em.createQuery("SELECT o FROM " + clzss.getSimpleName() + " o").getResultList();
	}
	
	@Override
	public <T> void remove(T entity, Object... ids) {
		for (Object id : ids) {
			em.remove(em.getReference(entity.getClass(), id));
			//em.remove(em.find(entity.getClass(), id));
			//em.remove(em.merge(id));
		}
	}
	
	@Override
	public void remove(Object entity) {
		if(em.contains(entity)){
			
			log.info("remove a attached entity");
			em.remove(entity);
		}else{
			log.info("remove a detached entity");
			em.remove(em.merge(entity));
		}
	}
	
	@Remove
	@Override
	public void sync() {
		log.info("executing sync");
	}
}
