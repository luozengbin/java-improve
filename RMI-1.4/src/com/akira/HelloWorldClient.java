package com.akira;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class HelloWorldClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloWorld obj = null;
		try {
			// セキュリティマネージャーを設定します
			System.setSecurityManager(new RMISecurityManager());
			// リモートオブジェクトの参照(スタブ)を取得します
			obj = (HelloWorld) Naming.lookup("//localhost/MyObject");
			// リモートメソッドを呼び出してその戻り値を画面に表示します
			System.out.println(obj.sayHelloWorld());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
