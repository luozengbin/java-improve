package skillup.ejb30.basic.jpa;


import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class GenerationServiceImplTest {

	@Test
	public void testGeneratedByTable() throws Exception {
		GenerationService service = ContextUtils.lookup("skillup_ejb30_basic/GenerationServiceImpl/remote");
		
		service.insert(10);
	}
	
	@Test
	public void testUpdate() throws Exception {
		GenerationService service = ContextUtils.lookup("skillup_ejb30_basic/GenerationServiceImpl/remote");
		
		System.out.println("###" + service.update(11, "zzzz"));
	}
	
	@Test
	public void testFind() throws Exception {
		GenerationService service = ContextUtils.lookup("skillup_ejb30_basic/GenerationServiceImpl/remote");
		
		System.out.println(service.find(11));
	}
}
