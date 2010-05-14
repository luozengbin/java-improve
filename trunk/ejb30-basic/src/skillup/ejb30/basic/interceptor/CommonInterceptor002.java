package skillup.ejb30.basic.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CommonInterceptor002 {
	
	static final Log log = LogFactory.getLog(CommonInterceptor002.class);
	
	@AroundInvoke
	public Object execute(InvocationContext ctx) throws Exception{
		
		log.info("CommonInterceptor002 apply on " + ctx.getTarget().getClass().getName());
		
		return ctx.proceed();
		
	}

}
