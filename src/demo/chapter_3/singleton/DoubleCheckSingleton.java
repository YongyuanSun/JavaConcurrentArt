package demo.chapter_3.singleton;

public class DoubleCheckSingleton {
	private volatile static Instance instance;
	public static Instance getInstance(){
		if(instance==null){
			synchronized (DoubleCheckSingleton.class){
				if(instance==null){
					instance = new Instance();
				}
			}
		}
		return instance;
	}
}
