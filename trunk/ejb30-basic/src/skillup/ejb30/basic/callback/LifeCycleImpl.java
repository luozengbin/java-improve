package skillup.ejb30.basic.callback;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Init;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//@PostConstruct→@Init→@PrePassivate→@PostActivate→@Remove→@PreDestroy
@Stateful
@Remote({LifeCycle.class})
public class LifeCycleImpl implements LifeCycle {
	
	static final Log log = LogFactory.getLog(LifeCycleImpl.class);
	

	@Override
	public String say(String arg) {
		
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return arg;
	}
	
	
	@PostConstruct
	public void postConstruct(){
		log.info("postConstruct call by contanier");
	}
	
	@Init
	public void init(){
		log.info("init call by contanier start !!!");
		
		
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("init call by contanier end !!!");
		
	}
	
	@PreDestroy
	public void preDestroy(){
		log.info("preDestroy call by contanier");
	}
	
	@PrePassivate
	public void prePassivate(){
		log.info("prePassivate call by contanier");
	}
	
	@PostActivate
	public void postActivate(){
		log.info("postActivate call by contanier");
	}

	@Override
	@Remove
	public void stopSession() {
		log.info("stopSession call by contanier");
	}
}
