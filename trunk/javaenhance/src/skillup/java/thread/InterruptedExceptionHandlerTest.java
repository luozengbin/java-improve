package skillup.java.thread;

import java.lang.Thread.UncaughtExceptionHandler;

public class InterruptedExceptionHandlerTest {
	
	static class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			
			System.out.println(t.getName() + ":" + e.getMessage());
			Thread thread;
			try {
				thread = t.getClass().newInstance();
				thread.setUncaughtExceptionHandler(this);
				thread.start();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Thread thread = new Thread(){
			
			private int a = 10;
			
			public void run(){
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					System.out.println(Thread.currentThread().getName() + ":" + " a = " + a + " running... "+ a / a);
					a--;
				}
			}
		};
		
		thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		thread.start();
		
	}
}
