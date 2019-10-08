package demo;

import sun.misc.Unsafe;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static int[] solution(int[] arr,int a){
		if(arr==null||arr.length==0){
			return new int[]{a};
		}
		int[] res=new int[arr.length+1];
		//a比第一个都小
		if(a<=arr[0]){
			res[0]=a;
			for (int i = 0; i < arr.length; i++) {
				res[i+1]=arr[i];
			}
			return res;
		}
		//a比最后一个都大
		if(a>arr[arr.length-1]){
			res[arr.length-1]=a;
			for (int i = 0; i < arr.length; i++) {
				res[i]=arr[i];
			}
			return res;
		}
		//a在中间
		int i;
		for(i=0;i<arr.length;i++){
			if(arr[i]<a&&arr[i+1]>=a){
				break;
			}
		}
		for(int j=0;j<=i;j++){
			res[j]=arr[j];
		}
		res[i+1]=a;
		for (int j=i+2;j<arr.length+1;j++){
			res[j]=arr[j-1];
		}
		return res;

	}
	public void compareAndSwap(int val,int update){
		Unsafe unsafe=Unsafe.getUnsafe();
		unsafe.compareAndSwapInt(this,1L,val,update);
	}

	public static void main(String[] args) throws InterruptedException {




	}
}
