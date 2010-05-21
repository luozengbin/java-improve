package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateful
@Remote(EntityService.class)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EntityServiceImplExt implements EntityService {
	
	static final Log log = LogFactory.getLog(EntityServiceImplExt.class);
	
	@PersistenceContext(unitName="ejb30-basic", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public <T> T getEntity(Class<T> clzss, Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getEntityList(Class<T> clzss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persistEntity(Object... entitys) {
		for (Object object : entitys) {
			em.persist(object);
		}
	}
	
	
	@Remove
	@Override
	public void sync() {
		log.info("sync current persistence context to database");
	}

}
