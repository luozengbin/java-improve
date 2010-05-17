package skillup.ejb30.basic.interceptor;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
@Remote(InterceptorAware.class)
@Interceptors(LocalEventInterceptor.class)
public class EventInterceptor001Impl implements InterceptorAware {
	
	
	static final Log log = LogFactory.getLog(EventInterceptor001Impl.class);
	
	@PostConstruct
	public void postConstruct(){
		log.info("executing postConstruct");
	}
	
	@Override
	public void method001(String ... args) {

	}

	@Override
	public void method002(String ... args) {
		
	}
	
}
