package demo.chapter_4;

/**
 * synchronized方法和synchronized同步块的区别
 *  public static synchronized void test1();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
 *     Code:
 *       stack=0, locals=0, args_size=0
 *          0: return
 *       LineNumberTable:
 *         line 6: 0
 *
 *   public static void test2();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=2, args_size=0
 *          0: ldc           #2                  // class demo/chapter_4/SynchronizedDemo
 *          2: dup
 *          3: astore_0
 *          4: monitorenter
 *          5: aload_0
 *          6: monitorexit
 *          7: goto          15
 *         10: astore_1
 *         11: aload_0
 *         12: monitorexit
 *         13: aload_1
 *         14: athrow
 *         15: return
 *
 */
public class SynchronizedDemo {
	public static synchronized void test1(){

	}
	public static void test2(){
		synchronized (SynchronizedDemo.class){

		}
	}
}
