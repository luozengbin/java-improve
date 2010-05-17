package skillup.ejb30.basic.callback;

import org.junit.BeforeClass;
import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class LifeCycleBeanTest {
	
	static LifeCycle lifeCycle;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		lifeCycle = ContextUtils.lookup("skillup_ejb30_basic/LifeCycleImpl/remote");

	}

	@Test
	public void testSay() {
		
		lifeCycle.say("hello ejb3");
		
		System.out.println("after 10 mins the ejb object will be passivate by ejb container!!!");
		
		try {
			Thread.sleep(1000 * 60 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("ejb object will be remove by ejb container!!!");
		lifeCycle.stopSession();
	}

}
