package skillup.java.basic;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

//オートボクシングテスト
//参考サイト：http://www.techscore.com/tech/J2SE/JavaLanguage/3.html
public class AutoBoxingTest {
	
	//オートボクシングテスト
	public void testBoxing() throws Exception {
		
		int a = 100;
		Integer i = a;
		
		//in jdk 1.4
		//int a = 100;
		//Integer i = new Integer(a); or Integer i = (Integer)a; 
		
		Character c = 'c';
	}
	
	//アンボクシング
	@Test
	public void testUnBoxing() throws Exception {
		int i = new Integer(100);
		char c = new Character('c');
	}
	
	//ボクシングとアンボクシングおよびジェネリクスを組み合わせることにより、プリミティブ型値
	//を扱うコレクションを操作するプログラムが大幅に簡略化されます。
	public void testWithBoxingFunc() throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		list.add(100); // ボクシング
		list.add(200);
		int a = list.get(0); // アンボクシング
		int b = list.get(1);
		
	}
	//testWithBoxingFuncの反例
	public void testWithoutBoxingFunc() throws Exception {
		List list = new ArrayList();
		list.add(new Integer(100));
		list.add(new Integer(200));
		int a = ((Integer) list.get(0)).intValue();
		int b = ((Integer) list.get(1)).intValue();
	}
	
	
	@Test
	public void testNullpoint() throws Exception {
		Integer a = null;
		int b = a; // NullPointerException 発生
	}
	
	
	//ボクシング・アンボクシングを行うことにより適用可能なメソッドが複数存在する場合は、コンパイルエラーとなります。
	public void methodC(int i, long l) {  }
	public void methodC(Integer a, Long l) { }
	  
	public static void main (String[] args) {
		//methodC(100, 100L);  // コンパイルエラー
	}

	
	@Test
	public void testPrimitiveArray() throws Exception {
		
		Integer A = 100;
		Integer B = 100;
		
		//varA = varB && abs(varA) < 127なら同じオブジェクトが参照される
		assertEquals(true , A == B);
		
		A = 128;
		B = 128;
		
		//反例
		assertEquals(false , A == B);
		
		//primitive配列とボクシングの関係
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
