package demo.chapter_4;

/**
 *1、 线程被其他线程调用interrupt时不会立即停止，其他线程只能建议此线程停止。
 * 2、对于非阻塞线程只是把isInterrupt标志置为true。
 * 3、对于阻塞线程，又分为三种情况。（1）如果线程处于sleep, wait, join 等状态，那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常；
 * （2）如果线程处于I/O阻塞状态，将会抛出ClosedByInterruptException（IOException的子类）异常；
 * （3）如果线程在Selector上被阻塞，select方法将立即返回；
 */
public class InterruptDemo {
	public static void main(String[] args) {
		Thread sleepThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		sleepThread.setDaemon(true);
		Thread BusyThread=new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){

				}
			}
		});
		BusyThread.setDaemon(true);

		sleepThread.start();
		BusyThread.start();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sleepThread.interrupt();
		BusyThread.interrupt();
		System.out.println("sleepThread:"+sleepThread.isInterrupted());
		System.out.println("busyThread:"+BusyThread.isInterrupted());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
