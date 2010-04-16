package skillup.java.basic;

import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.Test;

public class CollectionTest {
	
	@Test
	public void testVector() throws Exception {
		Vector<Integer> vector = new Vector<Integer>();
		
		for (int i = 0; i < 10; i++) {
			vector.add(i);
		}
	}
	
	@Test
	public void testTreeSet() throws Exception {
		Set<String> set = new TreeSet<String>();
		set.add("japan");
		set.add("china");
		
		System.out.println(set);
	}
}
