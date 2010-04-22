package skillup.ejb30.basic.jpa;

import java.util.List;

public interface PersonService {
	
	public void save(String name);
	
	public void update(Person person);
	
	public void delete(String personId);
	
	public Person getPerson(String personId);
	
	public List<Person> getPersons();
}
