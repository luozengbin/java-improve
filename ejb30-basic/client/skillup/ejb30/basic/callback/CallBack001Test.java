package skillup.ejb30.basic.callback;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

public class CallBack001Test {

	static CallBack001 callBack001;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			
			callBack001 = (CallBack001)new InitialContext().lookup("skillup_ejb30_basic/CallBack001Impl/remote");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMethod001() {
		System.out.println(callBack001.method001("akira"));
	}

	@Test
	public void testMethod002() {
		System.out.println(callBack001.method001("nihao"));
	}

}
