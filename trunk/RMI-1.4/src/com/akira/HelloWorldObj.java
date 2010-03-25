package com.akira;


import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloWorldObj extends UnicastRemoteObject implements HelloWorld {

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
			// �Z�L�����e�B�}�l�[�W���[��ݒ肵�܂�
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			// �T�[�o�[���̃����[�g�I�u�W�F�N�g�𐶐����܂�
			HelloWorldObj obj = new HelloWorldObj();
			// �����[�g�I�u�W�F�N�g�ɐV�������O���֘A�t���܂�
			Naming.rebind("MyObject", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
