package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful
@Remote(EntityService.class)
public class StatefulEntityServiceImpl extends EmptyEntityServiceImpl {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getEntityList(Class<T> clzss) {
		return em.createQuery("SELECT o FROM " + clzss.getSimpleName() + " o").getResultList();
	}

	@Override
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public <T> T persistEntity(T entity) {
		em.persist(entity);
		return entity;
	}

}
