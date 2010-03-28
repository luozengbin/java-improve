package skillup.java.basic;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

/* import all the static method of Math*/
import static java.lang.Math.*;

import org.junit.Test;


public class TestStaticImport {
	
	@Test
	public void testAdd() {
		
		assertThat(true, is( 5 == abs(-5)));
		
		assertThat(true, is( 6 == max(3, 6)));
		
	}
}
