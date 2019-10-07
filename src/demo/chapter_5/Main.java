package demo.chapter_5;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
	public static void main(String[] args) {
		ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
		ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
		ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
		readLock.lock();
		writeLock.lock();
	}
}
