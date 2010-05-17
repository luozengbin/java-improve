package skillup.ejb30.basic.timerservice;


import javax.naming.InitialContext;

import org.junit.Test;

public class TimerServiceImplTest {

	@Test
	public void testTimerServiceImpl001() throws Exception {
		
		TimerServiceAware bean = (TimerServiceAware)new InitialContext().lookup("skillup_ejb30_basic/TimerServiceImpl001/remote");
		
		bean.scheduleTimer(1000 * 3);
		
	}

}
