package skillup.ejb30.basic.interceptor;

import java.util.Map.Entry;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloChinaInterceptor02 {

	static final Log log = LogFactory.getLog(HelloChinaInterceptor02.class);

	@AroundInvoke
	public Object updateParameter(InvocationContext ctx) throws Exception {

		Object[] newParameter = new Object[ctx.getParameters().length];

		for (Object parameter : ctx.getParameters()) {
			log.info("parameter list:" + parameter);

		}

		for (int i = 0; i < ctx.getParameters().length; i++) {
			newParameter[i] = "new$" + (String) ctx.getParameters()[i];

		}

		ctx.setParameters(newParameter);
		
		log.info("update parameter by HelloChinaInterceptor02");
		
		
		
		for (Entry<String, Object> entry : ctx.getContextData().entrySet()) {
			log.info("ContextData Map >>>> " + entry.getKey() + " : " + entry.getValue());
		}
		return ctx.proceed();

	}

}
