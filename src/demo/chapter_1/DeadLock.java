package demo.chapter_1;

public class DeadLock {
	private static final String A="A";
	private static final String B="B";

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					synchronized (A){
						System.out.println("this is A method in thread one");
						synchronized (B){
							System.out.println("this is B method in thread one");
						}
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					synchronized (B){
						System.out.println("this is B method in thread two");
						synchronized (A){
							System.out.println("this is A method in thread two");
						}
					}
				}
			}
		}).start();
	}
}
