package skillup.ejb30.basic.jndi;

import static org.junit.Assert.fail;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

public class Bean001ImplTest {
	
	static Bean001 bean;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			InitialContext itx = new InitialContext();
			bean = (Bean001) itx.lookup("skillup/ejb/Bean001");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMethod001() {
		System.out.println(bean.method001("nihao"));
	}

	@Test
	public void testMethod002() {
		System.out.println(bean.method001("akira"));
	}

}
