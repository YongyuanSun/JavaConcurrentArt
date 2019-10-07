package demo.chapter_3.singleton;

/**
 * 1、使用工厂模式，只有在调用getInstance()方法时才会实例化。
 * 2、外部类可以直接访问内部类的private成员，实际上类的top level class都可以访问其成员。
 */
public class InstanceFactory {
	private static class InstanceHolder{
		private static Instance instance=new Instance();
	}
	public static Instance getInstance(){
		return InstanceHolder.instance;
	}
}
