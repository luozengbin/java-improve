package skillup.java.mustang.jaxws;

import javax.jws.WebService;

@WebService
public class MyWebService {
	
	public double calculateBmi(double height, double weight) {
		return weight / (height * height);
	}

}
