package demo.chapter_3.singleton;


/**
 * 单例模式使用静态内部类的优点：如果一个类中有多个实例，调用静态内部类只会初始化一个实例，不会初始化所有实例（包括用不到的）。
 */
public class StaticInnerClassDemo {
	private static class InstanceHolder1{
		private static Instance instance=new Instance("instance1 init");
	}
	private static class InstanceHolder2{
		private static Instance instance=new Instance("insstance2 init");
	}
	private static Instance instance3=new Instance("instance3 init");
	private static Instance instance4=new Instance("instance4 init");
	public static Instance getInstance1(){
		return InstanceHolder1.instance;
	}
	public static Instance getInstance2(){
		return InstanceHolder2.instance;
	}
	public static Instance getInstance3(){
		return instance3;
	}
	public static Instance getInstance4(){
		return instance4;
	}

	public static void main(String[] args) {
		/**
		 * instance3 init
		 * instance4 init
		 * instance1 init
		 * 静态内部类初始化时静态变量会初始化，不相关的静态内部类不会初始化
		 */
		//StaticInnerClassDemo.getInstance1();

		//同上
		//StaticInnerClassDemo.getInstance2();

		/**
		 * instance3 init
		 * instance4 init
		 * 静态变量初始化时会初始化所有静态变量，不会初始化静态内部类。
		 */
		StaticInnerClassDemo.getInstance3();
		//StaticInnerClassDemo.getInstance4();
	}
}
