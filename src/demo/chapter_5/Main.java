package demo.chapter_5;

import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
	public static void main(String[] args) {
		ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
		ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
		ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
		readLock.lock();
		writeLock.lock();
		Lock lock1=new ReentrantLock();
		Condition condition = lock1.newCondition();
		try {
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		condition.signal();
		condition.signalAll();
		ConcurrentHashMap map;
		//map.size();
		Stack stack=new Stack<>();
		stack.peek();
	}
}
