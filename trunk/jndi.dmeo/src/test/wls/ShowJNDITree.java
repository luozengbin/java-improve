package test.wls;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class ShowJNDITree {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		
		Context context = new InitialContext();
		
//		Context subContext = (Context) context.lookup("akira"); 
//		//tcontext.createSubcontext("akira");
//		
//		subContext.rebind("style", "smart");
//		
//		subContext.unbind("style");
//		
//		context.unbind("akira");
		
		Hashtable<?,?> env = context.getEnvironment();
		Enumeration<?> keys = env.keys();
		
		System.out.println("---------------------Environment Information---------------------");
		Object key = null;
		while(keys.hasMoreElements()){
			key = keys.nextElement();
			System.out.println(key + " : " + env.get(key));
		}
		key = null;
		
		System.out.println("---------------------NameClassPair Information---------------------");
		
		printBaseInfo(context, 0);
		
		context.close();
	}
	
	private static void printBaseInfo(Context context, int depth) throws NamingException{
		
		NamingEnumeration<NameClassPair> list = context.list(".");
		NameClassPair nameClassPair = null;
		try {
			while(list.hasMore()){
				nameClassPair = list.next();
				
				for (int i = 0; i < depth; i++) {
					System.out.print("\t");
				}
				System.out.println(nameClassPair.getName() + " : " + nameClassPair.getClassName());
				
				Object sub = context.lookup(nameClassPair.getName());
				if(sub instanceof Context && !nameClassPair.getName().equals("weblogic"))
					printBaseInfo((Context)sub, depth + 1);
			}

		} catch (Exception e) {
			for (int i = 0; i < depth; i++) {
				System.out.print("\t");
			}
			System.out.println("FIELD ON " + list);
		}
		nameClassPair = null;
	}

}
