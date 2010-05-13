package skillup.ejb30.basic.jndi;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

//使用@Stateless的mappedName属性定义jndi名，这个之适用于remote接口
@Stateless(mappedName="skillup/ejb/Bean001")
@Remote({Bean001.class})
@Local({Bean001Local.class})
public class Bean001Impl implements Bean001, Bean001Local {

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
