package skillup.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		
		BusinessAware target = new BusinessBean();
		System.out.println(getProxy(target).getProducts());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(final T target){
		return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
						
						System.out.println("実行開始");
						
						long start = System.currentTimeMillis();
						
						Object retVal = method.invoke(target, args);
						
						long end = System.currentTimeMillis();
						
						System.out.println("実行終了");
						
						System.out.println("コスト：" + (end - start));
						
						return retVal;
					}
				}
		);
	}
}
