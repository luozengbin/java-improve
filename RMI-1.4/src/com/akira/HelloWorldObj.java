package com.akira;


import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class HelloWorldObj implements HelloWorld ,Serializable{

	private static final long serialVersionUID = 6558747930954000825L;

	protected HelloWorldObj() throws RemoteException {
		super();
	}

	@Override
	public String sayHelloWorld() throws RemoteException {
		return "Hello World";
	}

	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
			// セキュリティマネージャーを設定します
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			// サーバー側のリモートオブジェクトを生成します
			HelloWorldObj obj = new HelloWorldObj();
			// リモートオブジェクトに新しい名前を関連付けます
			Naming.rebind("MyObject", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
