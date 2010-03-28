package skillup.java.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestVarableParameter {
	
	
	@Test
	public void testAdd() throws Exception {
		
		assertEquals(add(1,2,3), 6);
		assertEquals(add(1,2,3,4,5), 15);
		
	}
	
	
	//可変長引数の定義
	public static int add(/*final*/ int...params){
		
		int result = 0;
		for (int i : params) {
			result += i;
		}
		return result;
	}
}
