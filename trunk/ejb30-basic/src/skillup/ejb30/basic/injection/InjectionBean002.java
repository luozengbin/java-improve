package skillup.ejb30.basic.injection;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(Injection.class)
@Local(InjectionLocal.class)
public class InjectionBean002 implements Injection,InjectionLocal {
	
	@Override
	public String sayHello(String name) {
		return "have a nice day " + name + "!!!";
	}
}
