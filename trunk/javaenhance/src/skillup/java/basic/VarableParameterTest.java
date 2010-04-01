package skillup.java.basic;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

//可変長パラメータ使用例
public class VarableParameterTest {
	
	@Test
	public void testAdd() throws Exception {
		
		assertEquals(add(1,2,3), 6);
		
		//引数の数が任意に変えられる
		assertEquals(add(1,2,3,4,5), 15);
		
	}
	
	
	//可変長引数の定義
	public static int add(/*final*/ int...params){
		
		//コンパイル後配列に見直されている
		assertThat(true, is(int[].class == params.getClass()));
		
		int result = 0;
		for (int i : params) {
			result += i;
		}
		return result;
	}
}
