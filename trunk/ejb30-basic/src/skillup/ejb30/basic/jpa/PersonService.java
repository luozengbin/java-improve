package skillup.ejb30.basic.jpa;

import java.util.List;

public interface PersonService {
	
	public Person save(Person person);
	
	public void update(Person person);
	
	public void delete(String personId);
	
	public Person getPerson(String personId);
	
	public List<Person> getPersons();
}
