package demo.chapter_4;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipeDemo {
	public static void main(String[] args) {
		//创建输入输出管道
		PipedWriter writer = new PipedWriter();
		PipedReader reader = new PipedReader();

		try {
			//管道连接
			writer.connect(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread printThread=new Thread(new Print(reader),"PrintThread");
		printThread.start();
		int receive=0;
		try {
			while ((receive=System.in.read())!=-1){
				writer.write(receive);
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	static class Print implements Runnable{
		private PipedReader reader;
		public Print(PipedReader reader){
			this.reader=reader;
		}
		@Override
		public void run() {
			int receive=0;
			try {
				while ((receive=reader.read())!=-1){
					System.out.print((char)receive);
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}

