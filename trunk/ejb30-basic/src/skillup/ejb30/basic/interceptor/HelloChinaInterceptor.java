package skillup.ejb30.basic.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloChinaInterceptor {
	
	static final Log log = LogFactory.getLog(HelloChinaInterceptor.class);
	
	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception{
		log.info("**************** HelloChina Interceptor Begin ****************");
		long start = System.currentTimeMillis();
		
		try {
			log.info("excuting method:" + ctx.getMethod().getName());
			
			ctx.getContextData().put("HelloChinaInterceptor-Done", true);
			
			return ctx.proceed();
			
		} catch (Exception e) {
			throw e;
		}finally{
			long time = System.currentTimeMillis() - start;
			log.info("HelloChinaInterceptor cost time:" + time);
			log.info("**************** HelloChina Interceptor End ****************");
		}
	}

}
