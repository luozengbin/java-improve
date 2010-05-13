package skillup.ejb30.basic.jndi;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;


//使用@RemoteBinding和@LocalBinding注解实现jndi名绑定
@Stateless//(name="MYNAME_Bean002")
@Remote({Bean002.class})
@org.jboss.ejb3.annotation.RemoteBinding(jndiBinding="skillup/ejb3/jndi/remote/test002")
@Local({Bean002Local.class})
@org.jboss.ejb3.annotation.LocalBinding(jndiBinding="skillup/ejb3/jndi/local/test002")
public class Bean002Impl implements Bean002, Bean002Local {

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
