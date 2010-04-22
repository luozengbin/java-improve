package skillup.ejb30.basic.stateless;

import static org.junit.Assert.*;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import skillup.ejb30.basic.jpa.PersonService;

public class OperationBeanTest {

	private static Operation operation;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			InitialContext itx = new InitialContext();
			operation = (Operation) itx.lookup("skillup_ejb30_basic/OperationBean/remote");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@Test
	public void testAddUp() {
		System.out.println(operation.addUp());
		System.out.println(operation.addUp());
		System.out.println(operation.addUp());
	}
	
}
