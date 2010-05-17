package skillup.ejb30.basic.injection;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(Injection.class)
@Local(InjectionLocal.class)
public class InjectionBean003 implements Injection,InjectionLocal {
	@Override
	public String sayHello(String name) {
		return "you got a new message from milan!!!";
	}
}
