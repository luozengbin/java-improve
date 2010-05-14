package skillup.ejb30.basic.interceptor;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(InterceptorAware.class)
public class Interceptor003Impl implements InterceptorAware {

	@Override
	public void method001(String... args) {

	}

	@Override
	public void method002(String... args) {

	}

}
