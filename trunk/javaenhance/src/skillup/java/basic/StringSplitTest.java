package skillup.java.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringSplitTest {

	@Test
	public void testOK() throws Exception {
		String str = ";;a;b;;;c;d;;;;";
		String[] tokens = str.split(";", -1);
		for (int i = 0; i < tokens.length; i++) {
			System.out.println("i" + i + ":" + tokens[i]);
		}
	}
	
	@Test
	public void testNG() throws Exception {
		String str = ";;a;b;;;c;d;;;;";
		String[] tokens = str.split(";");
		for (int i = 0; i < tokens.length; i++) {
			System.out.println("i" + i + ":" + tokens[i]);
		}
	}
}
