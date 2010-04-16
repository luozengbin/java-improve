package skillup.java.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTaskTest implements Callable<Date> {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableTaskTest task = new CallableTaskTest();
		
		ExecutorService ex = Executors.newSingleThreadExecutor();
		
		Future<Date> future = ex.submit(task);
		
		System.out.println(future.get());
		
		System.out.println("after submit");
		
		ex.shutdown();
	}

	@Override
	public Date call() throws Exception {
		Thread.sleep(3000);
		return Calendar.getInstance().getTime();
	}

}
