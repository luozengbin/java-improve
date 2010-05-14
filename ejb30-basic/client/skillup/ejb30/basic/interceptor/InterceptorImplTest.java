package skillup.ejb30.basic.interceptor;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

public class InterceptorImplTest {
	
	@Test
	public void testInterceptor002Impl() throws NamingException {
		InterceptorAware bean = (InterceptorAware)new InitialContext().lookup("skillup_ejb30_basic/Interceptor002Impl/remote");
		bean.method001("hello", "akira");
		bean.method002("how are you", "milan");
	}

	@Test
	public void testInterceptor003Impl() throws NamingException {
		InterceptorAware bean = (InterceptorAware)new InitialContext().lookup("skillup_ejb30_basic/Interceptor003Impl/remote");
		bean.method001("hello", "akira");
		bean.method002("how are you", "milan");
	}

}
