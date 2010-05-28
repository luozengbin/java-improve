package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(EntityService.class)
public class TempEntityServiceImpl extends EmptyEntityServiceImpl {
	
	@PersistenceContext(unitName = "ejb30-basic")
	private EntityManager em;
	
	public <T> T getEntity(Class<T> clzss, Object key) {
		return em.find(clzss, key);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getEntityList(Class<T> clzss) {
		
		return em.createQuery("SELECT o FROM Entity001 o where o.id = 3").getResultList();
		
		//return em.createQuery("SELECT o FROM " + clzss.getSimpleName() + " o").getResultList();
	}
}
