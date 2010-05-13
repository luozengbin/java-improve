package skillup.ejb30.basic.interceptor;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
@Remote(HelloChina.class)
@Local(HelloChinaLocal.class)
@Interceptors({HelloChinaInterceptor.class, HelloChinaInterceptor02.class})
public class HelloChinaImpl implements HelloChina, HelloChinaLocal {
	
	static final Log log = LogFactory.getLog(HelloChinaImpl.class);
	
	@Override
	public String myName() {
		
		Thread.dumpStack();
		
		return "akira";
	}

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

}
