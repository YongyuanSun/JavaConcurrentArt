package demo.chapter_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
	private static AtomicInteger casI=new AtomicInteger(0);
	private static int a=0;
	private static void casCount(){
		for (;;){
			int i=casI.get();
			boolean suc = casI.compareAndSet(i, ++i);
			if(suc){
				break;
			}
		}
	}
	private static void count(){
		a++;
	}
	private static final int CYCLE=100;
	public static void main(String[] args) throws InterruptedException {
		List<Thread> ts=new ArrayList<>();
		CountDownLatch latch=new CountDownLatch(CYCLE);
		for(int i=0;i<CYCLE;i++){
			Thread t=new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j=0;j<10000;j++){
						casCount();
						count();
					}
					latch.countDown();
				}
			});
			ts.add(t);
		}
		for(Thread t:ts){
			t.start();
		}
		latch.await();
		System.out.println("cas result:"+casI);
		System.out.println("unsafe result:"+a);
	}
}
