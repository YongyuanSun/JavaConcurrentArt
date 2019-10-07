package demo.chapter_1;

public class ConcurrencyTest {
	private static final long count =100;

	public static void main(String[] args) throws InterruptedException {
		concurrency();
		serial();
	}

	private static void serial() {
		long start=System.currentTimeMillis();
		long a=0;
		for (int i = 0; i < count; i++) {
			a+=5;
		}
		long b=0;
		for (int i = 0; i < count; i++) {
			b--;
		}
		long interval=System.currentTimeMillis()-start;
		System.out.println("串行花费时间："+interval);
	}

	private static void concurrency() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				long a=0;
				for (long i = 0; i < count; i++) {
					a+=5;
				}
//				try {
//					Thread.currentThread().sleep(3);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		});
		t.start();
		int b=0;
		for (int i = 0; i < count; i++) {
			b--;
		}

		t.join();
		long interval = System.currentTimeMillis()-start;
		System.out.println("并行花费时间："+interval);
	}
}
