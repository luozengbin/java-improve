package skillup.java.reflect;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TestReflectBasic {
	
	@SuppressWarnings("unchecked")
	@Test
	public void testClassByteCode() throws ClassNotFoundException{
		
		Class cls1 = String.class;
		
		Class cls2 = "Hello".getClass();
		
		Class cls3 = Class.forName("java.lang.String");
		
		assertTrue(cls1 == cls2);
		
		assertTrue(cls1 == cls3);
		
	}
	

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
	}
	
	@Test
	public void testArrayType(){
		assertThat(true, is(int[].class.isArray()));	
	}
}
