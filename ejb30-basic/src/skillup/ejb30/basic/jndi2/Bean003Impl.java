package skillup.ejb30.basic.jndi2;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

//EJB名がskillup.ejb30.basic.jndi.Bean003Implと競争しているため、自前のEJB名前を指定する
@Stateless(name="my_Bean003Impl")
@Remote({Bean003.class})
@Local({Bean003Local.class})
public class Bean003Impl implements Bean003, Bean003Local {

	@Override
	public String method001(String... parameter) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < parameter.length; i++) {
			sb.append(parameter[i]).append(",");
		}
		
		return sb.toString();
	}
	
	@Override
	public String method002(String... parameter) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < parameter.length; i++) {
			sb.append(parameter[i]).append(",");
		}
		
		return sb.toString();
	}

}
