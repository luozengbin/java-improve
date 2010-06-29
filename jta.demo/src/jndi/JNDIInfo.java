package jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class JNDIInfo {

	
	public static void  bindSubContext(String contextname) throws NamingException {
		
		Context context = new InitialContext();
		
		context = (Context)context.lookup("java:/");
		
		context.createSubcontext(contextname);
		
		context.close();
	}
	
	
	public static String  getInfo() throws NamingException {
		
		Context context = new InitialContext();
		
		context = (Context)context.lookup("java:/");
		
		String info = getJndiInfo(context, 0);
		
		context.close();
		
		return info;
	}
	
	private static String getJndiInfo(Context context, int depth) throws NamingException{
		
		StringBuffer result = new StringBuffer();
		
		NamingEnumeration<NameClassPair> list = context.list("");
		NameClassPair nameClassPair = null;
		try {
			while(list.hasMore()){
				nameClassPair = list.next();
				
				for (int i = 0; i < depth; i++) {
					result.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				}
				result.append(nameClassPair.getName() + " : " + nameClassPair.getClassName() + "<br/>");
				
				Object sub = context.lookup(nameClassPair.getName());
				if(sub instanceof Context && !nameClassPair.getName().equals("weblogic"))
					result.append(getJndiInfo((Context)sub, depth + 1));
			}

		} catch (Exception e) {
			for (int i = 0; i < depth; i++) {
				result.append("\t");
			}
			result.append("FIELD ON " + list);
		}
		nameClassPair = null;
		
		return result.toString();
	}
	

}
