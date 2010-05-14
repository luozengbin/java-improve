package skillup.ejb30.basic.interceptor;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import skillup.help.CommonUtils;

@Stateless
@Remote(InterceptorAware.class)
public class Interceptor002Impl implements InterceptorAware {

	static final Log log = LogFactory.getLog(Interceptor002Impl.class);

	@Override
	public void method001(String... args) {
		// doing nothing
	}

	@Override
	public void method002(String... args) {
		// doing nothing
	}

	@AroundInvoke
	public Object log1(InvocationContext ctx) throws Exception {

		try {

			log.info(ctx.getMethod().getName() + "starting>>>");

			return ctx.proceed();
		} catch (Exception e) {

			log.error(e);
			throw e;
		} finally {
			log.info(ctx.getMethod().getName() + "end>>>");
		}
	}

	@AroundInvoke
	public Object log2(InvocationContext ctx) throws Exception {

		log.info("Parameter:" + CommonUtils.toString((String[])ctx.getParameters()[0]));
		return ctx.proceed();
	}

}
