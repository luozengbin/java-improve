package skillup.ejb30.basic.jndi;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote({Bean004.class})
@Local({Bean004Local.class})
public class Bean004Impl implements Bean004, Bean004Local {

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
