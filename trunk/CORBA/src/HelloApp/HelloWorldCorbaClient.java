package HelloApp;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class HelloWorldCorbaClient {
	static Hello helloImpl;

	public static void main(String args[]) {
		try {
			// ORB�̐����Ə��������s���܂�
			ORB orb = ORB.init(args, null);

			// �l�[���T�[�r�X���������ăl�[���T�[�r�X�̎Q�Ƃ��擾���܂�
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// �l�[���T�[�r�X����Hello�I�u�W�F�N�g�̎Q�Ƃ��擾���܂�
			String name = "Hello";
			helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));

			// sayHello()���\�b�h�����s���܂�
			System.out.println(helloImpl.sayHello());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
