
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class TestCase001 {

	@Test
	public void test001() throws Exception {
		
		
		List<String> noPreferredPortProcessIds = new ArrayList<String>();
		noPreferredPortProcessIds = Arrays.asList(("aaa").split("[,]"));
		
		for (String string : noPreferredPortProcessIds) {
			System.out.println(string);
		}
	}

}
