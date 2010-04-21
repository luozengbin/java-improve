package skillup.ejb30.basic.jpa;

import static org.junit.Assert.*;

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
		personService.save("akira");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPersons() {
		fail("Not yet implemented");
	}

}
