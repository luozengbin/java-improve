package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
@Remote(EntityService.class)
public class AMEEntityServiceImpl extends EmptyEntityServiceImpl {
	
	@PersistenceUnit(unitName="ejb30-basic")
	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	@PostConstruct
	public void init(){
		em = emf.createEntityManager();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getEntityList(Class<T> clzss) {
		return em.createQuery("SELECT o FROM " + clzss.getSimpleName() + " o").getResultList();
	}

	@Override
	public <T> T persistEntity(T entity) {
		em.persist(entity);
		em.flush();
		em.clear();
		return entity;
	}
	
	@PreDestroy
	public void preDestroy(){
		em.close();
	}

}
