package skillup.java.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorTest {
	
	
	public static void main(String[] args) {
		
		//Executor ex = Executors.newSingleThreadExecutor();
		
		//Executor ex = Executors.newFixedThreadPool(2);
		
		Executor ex = Executors.newCachedThreadPool();
		
		Runnable r1 = new Runnable(){
			@Override
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName() + " : r1" + System.currentTimeMillis());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Runnable r2 = new Runnable(){
			@Override
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName() + " : r2" + System.currentTimeMillis());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		ex.execute(r1);
		ex.execute(r2);
	}
	
}
