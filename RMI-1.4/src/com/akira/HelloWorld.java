package com.akira;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWorld extends Remote {
	
	String sayHelloWorld() throws RemoteException;
}
