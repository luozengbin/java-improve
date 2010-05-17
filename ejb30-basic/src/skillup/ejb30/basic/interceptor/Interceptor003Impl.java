package skillup.ejb30.basic.interceptor;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeDefaultInterceptors;

@Stateless
@Remote(InterceptorAware.class)
@ExcludeDefaultInterceptors
public class Interceptor003Impl implements InterceptorAware {

	@Override
	public void method001(String... args) {

	}

	@Override
	public void method002(String... args) {

	}

}
