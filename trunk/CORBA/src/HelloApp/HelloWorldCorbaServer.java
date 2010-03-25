package HelloApp;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class HelloWorldCorbaServer {

	public static void main(String args[]) {
		try {
			// ORB�̐����Ə��������s���܂�
			ORB orb = ORB.init(args, null);

			// RootPOA�̎Q�Ƃ��擾��POAManager���g�p�\�ɂ��܂�
			POA rootpoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// �T�[�o���g�𐶐����A�����ORB��o�^���܂�
			HelloImpl helloImpl = new HelloImpl();
			helloImpl.setORB(orb);

			// �T�[�o���g����Hello�I�u�W�F�N�g�̎Q�Ƃ��擾���܂�
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
			Hello href = HelloHelper.narrow(ref);

			// �l�[���T�[�r�X���������ăl�[���T�[�r�X�̎Q�Ƃ��擾���܂�
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// Hello�I�u�W�F�N�g�̎Q�Ƃ��l�[���T�[�r�X�ɓo�^���܂�
			String name = "Hello";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			System.out.println("HelloWorldCorbaServer���N�����܂���");

			// �N���C�A���g����̌Ăяo����҂��܂�
			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("HelloWorldCorbaServer���~���܂�");
	}

}
