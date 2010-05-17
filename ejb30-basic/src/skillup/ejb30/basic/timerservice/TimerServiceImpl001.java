package skillup.ejb30.basic.timerservice;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
@Remote
public class TimerServiceImpl001 implements TimerServiceAware {
	
	static final Log log = LogFactory.getLog(TimerServiceImpl001.class);
	

	@Resource private TimerService tms;
	
	int count = 0;
	
	@Override
	public void scheduleTimer(long milliseconds) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 10);
		
		tms.createTimer(calendar.getTime(), milliseconds, "this is a simple timer service application");
	}
	
	@Timeout
	public void timeoutHandler(Timer timer){
		count++;
		log.info("-------------------count :" + count + "-------------------");
		log.info("timer.getInfo():" + timer.getInfo());
		if(count >= 5){
			timer.cancel();
		}
	}
	
}
