package demo.chapter_4;

public interface ThreadPool<Job extends Runnable> {
	void execute(Job job);

	void shutdown();

	void addWorkers(int num);

	void removeWorkers(int num);

	int getJobSize();
}
