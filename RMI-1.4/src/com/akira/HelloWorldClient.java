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
			// �Z�L�����e�B�}�l�[�W���[��ݒ肵�܂�
			System.setSecurityManager(new RMISecurityManager());
			// �����[�g�I�u�W�F�N�g�̎Q��(�X�^�u)���擾���܂�
			obj = (HelloWorld) Naming.lookup("//localhost/MyObject");
			// �����[�g���\�b�h���Ăяo���Ă��̖߂�l����ʂɕ\�����܂�
			System.out.println(obj.sayHelloWorld());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
