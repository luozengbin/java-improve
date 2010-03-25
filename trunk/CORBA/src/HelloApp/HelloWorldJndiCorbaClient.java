package HelloApp;

import java.util.Hashtable;

import org.omg.CORBA.ORB;

public class HelloWorldJndiCorbaClient {
	static Hello helloImpl;

	public static void main(String args[]) {
		try {
			// ORB�̐����Ə��������s���܂�
			ORB orb = ORB.init(args, null);

			/*
			 * �R�����g�A�E�g org.omg.CORBA.Object objRef =
			 * orb.resolve_initial_references("NameService"); NamingContextExt
			 * ncRef = NamingContextExtHelper.narrow(objRef);
			 */
			/*** �ǉ� ***/
			Hashtable env = new Hashtable();
			env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.cosnaming.CNCtxFactory");
			env.put("java.naming.corba.orb", orb);
			// �l�[�~���O�R���e�L�X�g�̐���
			javax.naming.Context ic = new javax.naming.InitialContext(env);

			/*
			 * �R�����g�A�E�g String name = "Hello"; helloImpl =
			 * HelloHelper.narrow(ncRef.resolve_str(name));
			 * System.out.println(helloImpl.sayHello());
			 */
			/*** �ǉ� ***/
			String name = "Hello";
			// ���b�N�A�b�v
			helloImpl = HelloHelper.narrow(((org.omg.CORBA.Object) ic
					.lookup(name)));
			System.out.println(helloImpl.sayHello());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
