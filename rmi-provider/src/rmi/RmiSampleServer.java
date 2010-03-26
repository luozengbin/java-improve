package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiSampleServer extends UnicastRemoteObject implements RmiSample {

	protected RmiSampleServer() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = -3498752144688666402L;

	@Override
	public String getMessage() /* throws RemoteException ← 不要 */ {
		return this.getClass().toString() + "message from remote server";
	}
}
