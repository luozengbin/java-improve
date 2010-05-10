package skillup.java.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class RootDirClassLoader extends ClassLoader {

	public RootDirClassLoader() {
		super();
	}

	public RootDirClassLoader(ClassLoader parent) {
		super(parent);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		String clzssFilePath = (new StringBuilder()).append("/temp/classes/").append(name).append(".class").toString();
		
		try {
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			FileInputStream fis = new FileInputStream(clzssFilePath);
			
			int b = -1;

			while((b = fis.read()) != -1){
				bos.write(b);
			}
			
			fis.close();
			
			return defineClass(null, bos.toByteArray(), 0, bos.toByteArray().length);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
