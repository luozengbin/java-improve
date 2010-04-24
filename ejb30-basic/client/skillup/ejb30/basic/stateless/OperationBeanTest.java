package skillup.ejb30.basic.stateless;

import static org.junit.Assert.*;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

public class OperationBeanTest {



	private static Operation getNewSessionBean() {
		try {
			InitialContext itx = new InitialContext();
			return (Operation) itx.lookup("skillup_ejb30_basic/OperationBean/remote");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	@Test
	public void testAddUp() {
		
		Operation operation = getNewSessionBean();
		
		System.out.println(operation.addUp());
		System.out.println(operation.addUp());
		System.out.println(operation.addUp());
		testAddUp2();
	}
	
	//@Test
	public void testAddUp2() {
		
		Operation operation = getNewSessionBean();
		
		System.out.println(operation.addUp());
		System.out.println(operation.addUp());
		System.out.println(operation.addUp());
	}
	
}
