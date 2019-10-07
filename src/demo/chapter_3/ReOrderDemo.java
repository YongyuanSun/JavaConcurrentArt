package demo.chapter_3;

public class ReOrderDemo {
	int a=0;
	boolean flag=false;
	private void setA(){
		a=1;
		flag=true;
	}
	private void getA(){
		while (true){
			if(flag){
				System.out.println(a*a);
				break;
			}
		}
	}
	public static void main(String[] args) {
		ReOrderDemo instance=new ReOrderDemo();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				instance.setA();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				instance.getA();
			}
		});
		t2.start();
		t1.start();


	}
}
