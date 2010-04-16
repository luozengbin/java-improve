package skillup.java.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest implements Runnable {

	int number;
	long start;

	public ScheduleTest(int number, long start) {
		this.number = number;
		this.start = start;
	}

	public void run() {
		System.out.print("Task" + number + " Start");
		System.out.println("(" + (System.currentTimeMillis() - start) + ")");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("Task" + number + " End  ");
		System.out.println("(" + (System.currentTimeMillis() - start) + ")");
	}

	public static void main(String[] args) throws InterruptedException {
		
		ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 3; i++) {
			System.out.print("Task" + i + " Send ");
			System.out.println("(" + (System.currentTimeMillis() - start) + ")");
			
			//一回だけ実行する
			//ex.schedule(new ScheduleTest(i, start), 1000, TimeUnit.MILLISECONDS);
			
			//一定間隔で実行する
			ex.scheduleAtFixedRate(new ScheduleTest(i, start), 1000, 5000, TimeUnit.MILLISECONDS);
		}
		
		Thread.sleep(30000);
		
		ex.shutdown();

	}
}
