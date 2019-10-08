package demo.chapter_8;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangeDemo {
	private static final Exchanger<String> EXCHANGER=new Exchanger<>();
	private static ExecutorService service=Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		Runnable taskA = new Runnable() {
			@Override
			public void run() {
				String A = "stream A";
				try {
					EXCHANGER.exchange(A);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		service.execute(taskA);
		Runnable taskB = new Runnable() {
			@Override
			public void run() {
				String B = "Stream B";
				try {
					String A = EXCHANGER.exchange(B);
					System.out.println(A.equals(B) + "/r/n" + A + "//n" + B);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		service.execute(taskB);
		service.shutdown();
	}
}
