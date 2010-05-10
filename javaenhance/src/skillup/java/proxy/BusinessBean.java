package skillup.java.proxy;

import java.util.ArrayList;
import java.util.List;

public class BusinessBean implements BusinessAware {

	@Override
	public List<String> getProducts() {
		
		List<String> result = new ArrayList<String>();
		
		result.add("p1");
		result.add("p2");
		result.add("p3");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
