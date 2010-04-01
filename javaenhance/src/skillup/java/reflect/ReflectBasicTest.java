package skillup.java.reflect;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import skillup.java.bean.Product;


public class ReflectBasicTest {
	
	//メイン関数
	public static void main(String[] args) {
		
		for (int i = 0; i < args.length; i++) {
			System.out.println("args:" + args[i]);
		}
		
	}
	
	@Test
	public void testInvokeMain() throws Exception {
		Method targetMainMethod = ReflectBasicTest.class.getMethod("main", String[].class);
		
		//targetMainMethod.invoke(null, new Object[]{new String[]{"Hello", " World"}});
		
		targetMainMethod.invoke(null, (Object)new String[]{"Hello", " World"});
	}
	
	//Classの取得方法
	@SuppressWarnings("unchecked")
	@Test
	public void testClassByteCode() throws ClassNotFoundException{
		
		Class cls1 = String.class;
		
		Class cls2 = "Hello".getClass();
		
		Class cls3 = Class.forName("java.lang.String");
		
		assertTrue(cls1 == cls2);
		
		assertTrue(cls1 == cls3);
		
	}
	
	//プリミティブについて
	@Test
	public void testPrimitiveType(){
		
		/*System.out.println(int.class);
		System.out.println(void.class);*/
		
		/*
		 * There are nine predefined Class objects to represent
		 * the eight primitive types and void.  These are created by the Java
		 * Virtual Machine, and have the same names as the primitive types that
		 * they represent, namely 
		 * 		boolean
		 * 		byte
		 * 		char
		 * 		short
		 * 		int
		 * 		long
		 * 		float
		 * 		double
		 */
		assertThat(true, is(byte.class.isPrimitive()));
		assertThat(true, is(short.class.isPrimitive()));
		assertThat(true, is(int.class.isPrimitive()));
		assertThat(true, is(char.class.isPrimitive()));
		assertThat(true, is(long.class.isPrimitive()));
		assertThat(true, is(float.class.isPrimitive()));
		assertThat(true, is(double.class.isPrimitive()));
		assertThat(true, is(boolean.class.isPrimitive()));
		assertThat(true, is(void.class.isPrimitive()));
		assertThat(true, is(int.class == Integer.TYPE));
		assertThat(false, is(int.class == Integer.class));
		
		//プリミティブ型の配列はプリミティブではありません！！！
		assertThat(true, is(int[].class.isArray()));
		assertThat(false, is(int[].class.isPrimitive()));
	}
	
	
	//Constructorからインスタンスを作成する
	@Test
	public void testCreateInstance() throws Exception {
		
		Constructor<Product> productConstructor = Product.class.getConstructor(long.class, String.class, String.class, int[].class);
		
		Product product = productConstructor.newInstance(100L, "SUX600", "カメラ", new int[]{2010, 2011,2012});
		
		System.out.println(product);
		
		Constructor<?>[] productConstructors = Product.class.getConstructors();
		for (Constructor<?> constructor : productConstructors) {
			System.out.println(constructor);
		}
		
		//classから直接インスタンスを作る、ただし引数のないConstructorが用意されない
		//とjava.lang.InstantiationException例外が出ます
		Product product_1 = Product.class.newInstance();
		System.out.println(product_1);
	}
	
	//Fieldの取得と更新
	@Test
	public void testGetFieldInfo() throws Exception {
		
		final String BEFORE = "カメラ";
		
		final String AFTER = "高級カメラ";
		
		Product product = new Product(100L, "SUX600", BEFORE, new int[]{2010, 2011,2012});
		
		Field category_field = Product.class.getDeclaredField("category");
		
		//注意：アクセス設定がget / setメソッドが呼び出し前に設定しておかなければなりません。
		category_field.setAccessible(true);
		
		assertThat(true, is(BEFORE.equals(category_field.get(product))));
		
		//値変更
		category_field.set(product, AFTER);
		
		System.out.println(product);
	}
	
	//メソッドテスト
	@Test
	public void testMethod() throws Exception {
		
		Product product = new Product();
		
		Method setNameMethod = Product.class.getDeclaredMethod("setName", String.class);
		
		setNameMethod.setAccessible(true);
		
		setNameMethod.invoke(product, "nepia");
		
		System.out.println(product);
	}
	
	@Test
	public void testArrayRefelection() throws Exception {
		
		int[] serviceLife = new int[]{2010, 2011, 2012};
		
		System.out.println(serviceLife.getClass().getName());
		
		System.out.println(serviceLife);
		
	}
	
	//配列のリフレクション
	@Test
	public void testArrayType(){
		
		System.out.println(int[].class);
		System.out.println(long[].class);
		
		int[] a1 = new int[]{1,2,3};
		int[] a2 = new int[4];
		int[][] a3 = new int[2][3];
		String[] a4 = new String[]{"a","b","c"};
		System.out.println(a1.getClass() == a2.getClass());
		//System.out.println(a1.getClass() == a4.getClass());
		//System.out.println(a1.getClass() == a3.getClass());
		
		System.out.println(a1.getClass().getName());
		System.out.println(a1.getClass().getSuperclass().getName());
		System.out.println(a4.getClass().getSuperclass().getName());
		
		//primitive[] はオブジェクトと識別されている
		//primitive[][] はオブジェクト配列と識別されている
		final Object aObj1 = a1;
		final Object aObj2 = a4;
		//Object[] aObj3 = a1;
		final Object[] aObj4 = a3;
		final Object[] aObj5 = a4;
		
		System.out.println(a1);
		System.out.println(a4);
		
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a4));
		
		
		printObject(a4);
		
	}

	private void printObject(Object obj) {
		Class<?> clzss = obj.getClass();
		
		if(clzss.isArray()){
			int length = Array.getLength(obj);
			
			for (int i = 0; i < length; i++) {
				System.out.println(Array.get(obj, i));
			}
		}else{
			System.out.println(obj);
		}
	}
}
