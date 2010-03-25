package HelloApp;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class HelloWorldCorbaClient {
	static Hello helloImpl;

	public static void main(String args[]) {
		try {
			// ORBの生成と初期化を行います
			ORB orb = ORB.init(args, null);

			// ネームサービスを検索してネームサービスの参照を取得します
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// ネームサービスからHelloオブジェクトの参照を取得します
			String name = "Hello";
			helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));

			// sayHello()メソッドを実行します
			System.out.println(helloImpl.sayHello());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
