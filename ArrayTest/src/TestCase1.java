
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TestCase1 {
	
	@Test
	public void test000() throws Exception {
		List<String> libGroups = new ArrayList<String>();
		System.out.println((String[])libGroups.toArray());
	}
	
	@Test
	public void test001() throws Exception {
		List libGroups = new ArrayList();
		System.out.println((String[])libGroups.toArray());
	}
	
	@Test
	public void test002() throws Exception {
		Object[] a = new Object[10];
		String[] b = (String[])a;
	}
	
	@Test
	public void test003() throws Exception {
		String[] a = new String[10];
		Object[] b = (Object[])a;
	}
	
	@Test
	public void test004() throws Exception {
		int a = 10;
		long b = a;
		
		int[] aa = new int[10];
		//long[] bb = (long[])aa;
	}
	
	@Test
	public void test005() throws Exception {
		Y[] a = new Y[10];
		X[] b = (X[])a;
	}
	
	@Test
	public void test006() throws Exception {
		X[] a = new X[10];
		Y[] b = (Y[])a;
	}
	
	public static class X{
		
	}
	
	public static class Y extends X{
		
	}
}
