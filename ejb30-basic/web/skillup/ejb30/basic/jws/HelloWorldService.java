package skillup.ejb30.basic.jws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace = "http://akira.skillup.com", name = "HelloWorld", serviceName = "HelloWorldService")
@SOAPBinding(style=Style.DOCUMENT)
public class HelloWorldService {
	
	@WebMethod(operationName="doHello")
	public String sayHello(@WebParam(name="sender")String name){
		return "Hello webservice!!! from " + name;
	}

}
