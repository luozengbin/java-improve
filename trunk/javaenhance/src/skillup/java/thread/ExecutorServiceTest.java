package skillup.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService ex = Executors.newSingleThreadExecutor();
		//ExecutorService ex = Executors.newCachedThreadPool();

		System.out.println("[Sending Tasks....]");
		for (int i = 0; i < 3; i++) {
			ex.execute(new Thread() {
				
				int max = 3;
				
				int current = 0;
				
				@Override
				public void run() {
					while (true) {
						System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis());
						Thread.currentThread();
						current++;
						if(current == max){
							break;
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
							break;
						}
					}
				}
			});
			System.out.println("add task...");
		}
		
		System.out.println("[Finish Sending!]");
		
		ex.shutdown();
//		
//		System.out.println("[ShutDown]");
//
//		Thread.sleep(5000);
//
//		ex.shutdownNow();
//		
//		System.out.println("[ShutDownNow]");
	}
}
