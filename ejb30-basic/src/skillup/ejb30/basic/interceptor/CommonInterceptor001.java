package skillup.ejb30.basic.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CommonInterceptor001 {
	
	static final Log log = LogFactory.getLog(CommonInterceptor001.class);
	
	@AroundInvoke
	public Object execute(InvocationContext ctx) throws Exception{
		
		log.info("running ejb3 by client test case");
		
		return ctx.proceed();
		
	}

}
