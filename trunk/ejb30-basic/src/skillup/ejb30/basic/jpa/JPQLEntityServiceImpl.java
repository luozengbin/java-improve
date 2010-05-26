package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Remote(EntityService.class)
public class JPQLEntityServiceImpl extends EmptyEntityServiceImpl {
	
	@PersistenceContext(unitName="ejb30-basic")
	private EntityManager em;
	
	@Override
	public <T> T persistEntity(T entity) {
		Entity001 entity001 = (Entity001)entity;
		
		Query query = em.createNativeQuery("insert into entity001 values(:id, :name)");
		
		query.setParameter("name", entity001.getName());
		
		query.setParameter("id", entity001.getId());
		
		query.executeUpdate();
		
		//em.persist(entity);
		
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntity(Class<T> clzss, Object key) {
		
		Query query = em.createQuery("select * from entity001 e where e.id = :id)");
		
		query.setParameter("id", key);
		
		return (T)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getEntityList(Class<T> clzss) {
		
		Query query = em.createNativeQuery("select * from entity001", clzss);
		
		return query.getResultList();
	}
}
