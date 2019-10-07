package demo.chapter_3;

public class VolatileTest {
	private volatile long v;
	public void set(long v){
		this.v=v;
	}
	public void getAndIncrement(){
		v++;
	}
	public long get(){
		return this.v;
	}

	public static void main(String[] args) {
		VolatileTest test=new VolatileTest();
		test.set(5L);
		test.getAndIncrement();
		test.get();
	}
}
/**
 * volatile修饰的变量在读写时相当于以下类
 */
class VolatileEqualClass{
	private long v;
	public synchronized void set(long v){
		this.v=v;
	}
	public void getAndIncrement(){
		long temp=get();
		temp+=temp;
		set(temp);
	}
	public long get(){
		return this.v;
	}
}
