package demo.chapter_2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * i++多线程下不安全
 */
public class SelfAddDemo {
	private static int i;
	private static AtomicInteger a=new AtomicInteger();
	public static void main(String[] args) throws InterruptedException {
		i=0;
		a.set(0);
		CountDownLatch latch=new CountDownLatch(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j = 0; j < 100000; j++) {
					i++;
					a.getAndIncrement();
				}
				latch.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j = 0; j < 100000; j++) {
					i++;
					a.getAndIncrement();
				}
				latch.countDown();
			}
		}).start();
		latch.await();
		System.out.println("i="+i);
		System.out.println("a="+a);

	}
}
