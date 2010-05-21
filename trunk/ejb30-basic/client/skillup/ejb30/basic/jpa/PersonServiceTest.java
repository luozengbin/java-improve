package skillup.ejb30.basic.jpa;

import java.util.Calendar;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.BeforeClass;
import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class PersonServiceTest {

	private static PersonService personService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personService = ContextUtils.lookup("skillup_ejb30_basic/PersonServiceBean/remote");
	}

	@Test
	public void testSave() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		//UserTransaction tx = ContextUtils.lookup("UserTransaction");
		//tx.begin();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1984);
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DAY_OF_MONTH, 29);
		Person person = personService.save(new Person("akira", Sex.Boy, 25, calendar.getTime()));
		System.out.println(person.getId());
		//tx.commit();
	}
	
	@Test
	public void testGetPerson() {
		Person person = personService.getPerson("1");
		System.out.println(person.getPassword());
	}

//	@Test
//	public void testUpdate() {
//		
//		Person person = personService.getPerson("1");
//		
//		person.setName("milan");
//		
//		personService.update(person);
//	}
//
//	@Test
//	public void testDelete() {
//		
//		List<Person> persons = personService.getPersons();
//		personService.delete(personService.getPersons().get(persons.size() - 1).getId());
//	}
//

//
//	@Test
//	public void testGetPersons() {
//		List<Person> persons = personService.getPersons();
//		
//		for (Person person : persons) {
//			System.out.println(person);
//		}
//	}

}
