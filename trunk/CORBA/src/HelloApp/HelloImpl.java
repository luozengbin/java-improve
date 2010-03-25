package HelloApp;

import org.omg.CORBA.ORB;

public class HelloImpl extends HelloPOA {

	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}

	public String sayHello() {
		return "Hello World";
	}

	public void shutdown() {
		orb.shutdown(false);
	}

}
