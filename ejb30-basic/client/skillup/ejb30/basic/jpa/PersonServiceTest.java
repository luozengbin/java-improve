package skillup.ejb30.basic.jpa;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

public class PersonServiceTest {

	private static PersonService personService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			InitialContext itx = new InitialContext();
			personService = (PersonService) itx.lookup("PersonServiceBean/remote");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		for (int i = 0; i < 100; i++) {
			personService.save("akira" + i);
		}
	}

	@Test
	public void testUpdate() {
		
		Person person = personService.getPerson("1");
		
		person.setName("milan");
		
		personService.update(person);
	}

	@Test
	public void testDelete() {
		
		List<Person> persons = personService.getPersons();
		personService.delete(personService.getPersons().get(persons.size() - 1).getId());
	}

	@Test
	public void testGetPerson() {
		Person person = personService.getPerson("1");
	}

	@Test
	public void testGetPersons() {
		List<Person> persons = personService.getPersons();
		
		for (Person person : persons) {
			System.out.println(person);
		}
	}

}
