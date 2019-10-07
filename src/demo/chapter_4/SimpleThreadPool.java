package demo.chapter_4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleThreadPool<Job extends Runnable> implements ThreadPool<Job>{
	private static final int MAX_THREAD_NUMS=10;

	private static final int MIN_THREAD_NUMS=1;

	private static final int DEF_THREAD_NUMS=5;

	//存放job的队列
	private  final LinkedList<Job> jobs=new LinkedList();

	//存放线程的队列
	private final List<Worker> workers= Collections.synchronizedList(new LinkedList<Worker>());

	private int workNum=DEF_THREAD_NUMS;

	//线程的id；
	private AtomicLong threadNo=new AtomicLong();

	public SimpleThreadPool(){
		initThreadPool(DEF_THREAD_NUMS);
	}

	public SimpleThreadPool(int threadNum){
		workNum=threadNum>MAX_THREAD_NUMS?MAX_THREAD_NUMS:
				threadNum<MIN_THREAD_NUMS?MIN_THREAD_NUMS:
						threadNum;
		initThreadPool(threadNum);
	}

	/**
	 * 创建worker并将worker放入worker队列
	 * @param threadNum
	 */
	private void initThreadPool(int threadNum) {
		for (int i = 0; i < threadNum; i++) {
			Worker worker=new Worker();
			workers.add(worker);
			new Thread(worker,"Thread-"+threadNo.getAndIncrement())
					.start();
		}
	}


	@Override
	public void execute(Job job) {
		if(job!=null){
			synchronized (jobs){
				jobs.add(job);
				//新job到来时唤醒等待的workers
				jobs.notify();
			}
		}
	}

	@Override
	public void shutdown() {
		for(Worker worker:workers){
			worker.shutdown();
		}
	}

	@Override
	public void addWorkers(int num) {
		//是否需要使用jobs加锁？
		synchronized (jobs){
			if(num+workNum>MAX_THREAD_NUMS){
				num=MAX_THREAD_NUMS-workNum;
			}
			if(num>0){
				initThreadPool(num);
				workNum+=num;
			}
		}
	}

	@Override
	public void removeWorkers(int num) {
		synchronized (jobs){
			if(workNum-num<MIN_THREAD_NUMS){
				num=workNum-MIN_THREAD_NUMS;
			}
			if(num>0){
				int count=0;
				while (count<num){
					//这种删除线程的方式比较温和，先把worker从worker队列移除，再等待worker中任务执行完退出线程
					Worker removeWorker = workers.remove(0);
					removeWorker.shutdown();
				}
				workNum-=num;
			}
		}
	}

	@Override
	public int getJobSize() {
		return jobs.size();
	}

	private class Worker implements Runnable{
		private boolean isAlive=true;
		private Job job=null;
		@Override
		public void run() {
			while (isAlive){
				while (jobs.isEmpty()){
					try {
						jobs.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				job=jobs.remove();
				if(job!=null){
					job.run();
				}
			}
		}

		public void shutdown() {
			isAlive=false;
		}
	}
}
