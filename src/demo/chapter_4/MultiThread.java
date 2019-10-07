package demo.chapter_4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 打印出当前运行的线程
 */
public class MultiThread {
	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for(ThreadInfo info:threadInfos){
			System.out.println(info.getThreadId()+":"+info.getThreadName());
		}
		System.out.println(System.getProperties());
		try {
			Thread.currentThread().sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
