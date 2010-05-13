package skillup.ejb30.basic.interceptor;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

public class HelloChinaImplTest {
	
	static HelloChina helloChina;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			
			helloChina = (HelloChina)new InitialContext().lookup("skillup_ejb30_basic/HelloChinaImpl/remote");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testMyName() {
		System.out.println(helloChina.myName());
	}

	@Test
	public void testSayHello() {
		System.out.println(helloChina.sayHello(" akira"));
	}

}
