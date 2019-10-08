package demo.chapter_6;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Integer> {
	private static final int Threshold = 100;
	private int start;
	private int end;
	public ForkJoinDemo(int start,int end){
		this.start=start;
		this.end=end;
	}
	@Override
	protected Integer compute() {
		int sum=0;
		if((end-start)<=Threshold){
			for(int i=start;i<=end;i++){
				sum+=i;
			}
			return sum;
		}
		int middle=(end+start)/2;
		ForkJoinDemo leftTask = new ForkJoinDemo(start, middle);
		ForkJoinDemo rightTask = new ForkJoinDemo(middle+1, end);
		leftTask.fork();
		rightTask.fork();
		Integer leftSum = leftTask.join();
		Integer rightSum = rightTask.join();
		return leftSum+rightSum;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ForkJoinPool forkJoinPool=new ForkJoinPool();
		ForkJoinDemo task = new ForkJoinDemo(1, 10000);
		long start = System.currentTimeMillis();
		ForkJoinTask<Integer> res = forkJoinPool.submit(task);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		System.out.println(res.get());
	}
}
