package skillup.ejb30.basic.interceptor;

import javax.annotation.PostConstruct;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LocalEventInterceptor {
	
	static final Log log = LogFactory.getLog(LocalEventInterceptor.class);
	
	@PostConstruct
	public void postConstruct(InvocationContext ctx){
		
		try {
			log.info("before executing postConstruct");
			ctx.proceed();
			log.info("after executing postConstruct");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
