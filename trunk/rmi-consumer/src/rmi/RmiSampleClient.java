package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiSampleClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		//java.rmi.Naming#lookup()�ŁARMI���W�X�g���[�i�f�t�H���g�Ń|�[�g1099�j���烊���[�g�I�u�W�F�N�g�̃C���^�[�t�F�[�X���擾����B
		RmiSample rmiSample = (RmiSample)Naming.lookup(RmiSample.SERVICE_NAME);
		
		//��������Registry���g���Ď擾����B�i�Ƃ������ANaming�̓�����Registry���g���Ă���͗l�j
		/*
		Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
		Remote r = registry.lookup("RmiSampleName");
		*/
		
		System.out.println(rmiSample.getMessage());
		
	}
	
}
