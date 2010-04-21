package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class PersonServiceBean implements PersonService {
	
	@PersistenceContext(unitName="ejb30-basic")
	EntityManager em;
	
	@Override
	public void delete(String personId) {
		em.remove(em.getReference(Person.class, personId));
	}

	@Override
	public Person getPerson(String personId) {
		return em.find(Person.class, personId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersons() {
		return em.createQuery("select o from Person o").getResultList();
	}

	@Override
	public void save(String name) {
		
		Person person = new Person(name);
		em.persist(person);
	}

	@Override
	public void update(Person person) {
		em.merge(person);
	}

}
