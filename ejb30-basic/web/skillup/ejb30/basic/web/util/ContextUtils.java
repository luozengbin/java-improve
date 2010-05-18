package skillup.ejb30.basic.web.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class ContextUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> T lookup(String jndiName){
		try {
			return (T) new InitialContext().lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
