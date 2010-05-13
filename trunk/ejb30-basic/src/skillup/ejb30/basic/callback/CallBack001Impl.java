package skillup.ejb30.basic.callback;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
@Remote({CallBack001.class})
@Local({CallBack001Local.class})
public class CallBack001Impl implements CallBack001, CallBack001Local {

	final static Log log = LogFactory.getLog(CallBack001Impl.class); 
	
	@PostConstruct
	public void postConstruct(){
		
		//构造之后
		log.info("postConstruct call by ejb container");
	}
	
	@PreDestroy
	public void preDestroy(){
		
		//销毁之前
		log.info("preDestroy call by ejb container");
	}
	
	@Remove
	public void remove(){
		//删除之前
		log.info("remove call by ejb container");
	}
	
	@Override
	@Remove
	public String method001(String ... args) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]).append(",");
		}
		
		return sb.toString();
	}

	@Override
	public String method002(String ... args) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]).append(",");
		}
		
		return sb.toString();
	}

}
