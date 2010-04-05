package skillup.java.basic;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

//オートボクシングテスト
//参考サイト：http://www.techscore.com/tech/J2SE/JavaLanguage/3.html
public class AutoBoxingTest {
	
	@Test
	public void testPrimitiveArray() throws Exception {
		
		int[] primitiveArray = new int[]{1,2,3,4};
		System.out.println("printArray(primitiveArray)");
		printArray(primitiveArray);
		
		Integer[] integerArray = new Integer[]{1,2,3,4};
		System.out.println("printArray(integerArray)");
		printArray(integerArray);
		System.out.println("printArray((Object[])integerArray)");
		printArray((Object[])integerArray);
		
		System.out.println("printArray((Object)integerArray)");
		printArray((Object)integerArray);
		
		//apacheのArrayUtilsツールクラスの運用例、primitive[]→Object[]へ変換する
		System.out.println("printArray(ArrayUtils.toObject(primitiveArray);)");
		printArray(ArrayUtils.toObject(primitiveArray));
	}
	
	private <T> void printArray(T ... objArray) {
		for (T object : objArray) {
			System.out.println(object);
		}
	}
}
