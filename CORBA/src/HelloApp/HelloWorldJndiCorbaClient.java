package HelloApp;

import java.util.Hashtable;

import org.omg.CORBA.ORB;

public class HelloWorldJndiCorbaClient {
	static Hello helloImpl;

	public static void main(String args[]) {
		try {
			// ORBの生成と初期化を行います
			ORB orb = ORB.init(args, null);

			/*
			 * コメントアウト org.omg.CORBA.Object objRef =
			 * orb.resolve_initial_references("NameService"); NamingContextExt
			 * ncRef = NamingContextExtHelper.narrow(objRef);
			 */
			/*** 追加 ***/
			Hashtable env = new Hashtable();
			env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.cosnaming.CNCtxFactory");
			env.put("java.naming.corba.orb", orb);
			// ネーミングコンテキストの生成
			javax.naming.Context ic = new javax.naming.InitialContext(env);

			/*
			 * コメントアウト String name = "Hello"; helloImpl =
			 * HelloHelper.narrow(ncRef.resolve_str(name));
			 * System.out.println(helloImpl.sayHello());
			 */
			/*** 追加 ***/
			String name = "Hello";
			// ルックアップ
			helloImpl = HelloHelper.narrow(((org.omg.CORBA.Object) ic
					.lookup(name)));
			System.out.println(helloImpl.sayHello());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
