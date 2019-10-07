package demo.chapter_5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock的condition对象完成等待唤醒功能
 */
public class ConditionDemo {
	Lock lock=new ReentrantLock();
	Condition condition=lock.newCondition();

	//等待
	public void awaitTest(){
		lock.lock();
		try {
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	//唤醒
	public void notifyTest(){
		lock.lock();
		try {
			condition.signal();
			//condition.signalAll();
		}finally {
			lock.unlock();
		}
	}
}
