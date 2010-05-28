package skillup.ejb30.basic.jpa;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateful
@Remote(EntityService.class)
public class StatefulEntityServiceImpl extends EmptyEntityServiceImpl {
	
	static final Log log = LogFactory.getLog(StatefulEntityServiceImpl.class);
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	private Entity001 entity;
	
	@Override
	public <T> T getEntity(Class<T> clzss, Object key) {
		if(this.entity == null){
			log.info("load Entity001 from database!!!");
			this.entity = em.find(Entity001.class, key);
		}
		return (T)this.entity;
	}
	
	public <T> T persistEntity(T entity) {
		
//		em.setFlushMode(FlushModeType.AUTO);
//		
//		log.info("em:flushMode:" + em.getFlushMode());
		Entity001 newEntity = (Entity001) entity;
		if(this.entity != null){
			this.entity.setName(newEntity.getName());
		}else{
			em.persist(newEntity);
			this.entity = newEntity;
		}
		//em.flush();
		return (T)this.entity;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public <T> List<T> getEntityList(Class<T> clzss) {
//		return em.createQuery("SELECT o FROM " + clzss.getSimpleName() + " o").getResultList();
//	}
//
//	@Override
//	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//	public <T> T persistEntity(T entity) {
//		em.persist(entity);
//		return entity;
//	}

}
