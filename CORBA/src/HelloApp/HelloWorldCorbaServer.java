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
			// ORBの生成と初期化を行います
			ORB orb = ORB.init(args, null);

			// RootPOAの参照を取得しPOAManagerを使用可能にします
			POA rootpoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// サーバントを生成し、それにORBを登録します
			HelloImpl helloImpl = new HelloImpl();
			helloImpl.setORB(orb);

			// サーバントからHelloオブジェクトの参照を取得します
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
			Hello href = HelloHelper.narrow(ref);

			// ネームサービスを検索してネームサービスの参照を取得します
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// Helloオブジェクトの参照をネームサービスに登録します
			String name = "Hello";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			System.out.println("HelloWorldCorbaServerが起動しました");

			// クライアントからの呼び出しを待ちます
			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("HelloWorldCorbaServerを停止します");
	}

}
