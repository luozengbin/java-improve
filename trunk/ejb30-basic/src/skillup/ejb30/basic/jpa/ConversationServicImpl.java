package skillup.ejb30.basic.jpa;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceProperty;

@Stateful
@Remote(ConversationService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConversationServicImpl implements ConversationService {
	
	private  Entity001 entity;
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED,
			properties={
			@PersistenceProperty(name="org.hibernate.flushMode", value="MANUAL")})
	private EntityManager em;
	
	@Override
	public Entity001 getEntity(int id) {
		this.entity = em.find(Entity001.class, id);
		return this.entity;
	}

	@Override
	public void updateName(String name) {
		this.entity.setName(name);
	}
	
	@Remove
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void commit() {
		em.flush();
	}
}
