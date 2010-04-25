package skillup.java.mustang.jaxws;

import javax.xml.ws.Endpoint;

public class MyWebServiceMain {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/MyWebService",new MyWebService());
	}

}
