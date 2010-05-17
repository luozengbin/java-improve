package skillup.ejb30.basic.client.util;

import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class ContextUtils {
	
	public static <T> T lookup(String jndiName){
		try {
			return (T) new InitialContext().lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T lookup(String jndiName, String userName, String password){
		try {
			
			Properties prop = new Properties();
			prop.load(ContextUtils.class.getClassLoader().getResourceAsStream("jndi.properties"));
			prop.put(InitialContext.SECURITY_PRINCIPAL, userName);
			prop.put(InitialContext.SECURITY_CREDENTIALS, password);
			
			InitialContext ctx = new InitialContext(prop);
			
			return (T)ctx.lookup(jndiName);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
