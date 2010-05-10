package skillup.java.classloader;

import skillup.java.proxy.BusinessAware;
import skillup.java.proxy.BusinessBean;

public class ClassLoaderTest {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		RootDirClassLoader rl = new RootDirClassLoader();
		
		Class<?> businessBeanClzss = rl.loadClass("BusinessBean");
		
		BusinessAware businessAware = (BusinessAware) businessBeanClzss.newInstance();
		
		System.out.println(businessAware.getClass().getClassLoader());
		
		System.out.println(businessAware.getProducts());
		
		BusinessBean b2 = new BusinessBean();
		
		System.out.println(b2.getClass().getClassLoader());
		
		System.out.println(b2.getProducts());
		
		
	}

}
