package demo.chapter_3;

import java.util.concurrent.CountDownLatch;

public class CacheDemo {
	private static int a;
	private static int b;
	private static int x;
	private static int y;
	public static void main(String[] args) throws InterruptedException {
		a=0;
		b=0;
		Integer c=0;
		CountDownLatch latch=new CountDownLatch(2);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				a = 1;
				x = b;
				latch.countDown();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				b = 2;
				y = a;
				latch.countDown();
			}
		});
		t1.start();
		t2.start();
		latch.await();
		System.out.println("x:"+x+" y:"+y);

	}
}
