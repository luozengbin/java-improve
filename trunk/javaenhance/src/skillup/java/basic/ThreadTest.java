package skillup.java.basic;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ThreadTest {
	public static void main(String[] args) {
		// StackTraceElement[] elements =
		// Thread.currentThread().getStackTrace();
		// for (StackTraceElement element : elements) {
		// System.out.println(element);
		// }

		Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
		Iterator<Entry<Thread, StackTraceElement[]>>  itr = maps.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<Thread, StackTraceElement[]> entry = itr.next();
			Thread thread = (Thread) entry.getKey();
			StackTraceElement[] trace = (StackTraceElement[]) entry.getValue();

			System.out.println(thread);
			for (int i = 0; i < trace.length; i++) {
				System.out.println(trace[i]);
			}
			System.out.println();
		}

	}
}
