package skillup.ejb30.basic.injection;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(Injection.class)
@Local(InjectionLocal.class)
public class InjectionBean001 implements Injection,InjectionLocal {
	
	@Override
	public String sayHello(String name) {
		return "Hello " + name + "!!!";
	}
}
