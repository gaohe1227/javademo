package 多线程.交换器;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import com.mysql.jdbc.Buffer;

/**
 * @author 高鹤
 *
 * @date 2016年12月9日
 */
public class ProductExchanger {
	public static Exchanger<List<Integer>> exhanger = new Exchanger<List<Integer>>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread producer=new Thread(new Producer());
		Thread consumer=new Thread(new Consumer());
		producer.start();
		consumer.start();
		try {
			while(System.in.read()!='\n'){
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		producer.interrupt(); 
		consumer.interrupt();
	}

}

class Producer implements Runnable {
	private static List<Integer> buffer = new ArrayList<Integer>();
	private boolean okToken = true;
	private final int NUFFSIZE = 10;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int j = 0;
		while (okToken) {
			if (buffer.isEmpty()) {
				try {
					for (int i = 0; i < NUFFSIZE; i++) {
						buffer.add((int) Math.random() * 100);
					}
					Thread.sleep((int) Math.random() * 1000);
					System.out.print("Producer buffer: ");
					for (int i : buffer) {
						System.out.print(i + ",");
					}
					System.out.println();
					System.out.println("Exchanging ...");
					buffer = ProductExchanger.exhanger.exchange(buffer);// 交换:返回值是交换的缓冲区
				} catch (Exception e) {
					// TODO: handle exception
					okToken = false;
				}
			}
		}

	}

}

class Consumer implements Runnable {

	private static List<Integer> buffer = new ArrayList<Integer>();
	private boolean okToken = true;
	private final int NUFFSIZE = 10;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int j = 0;
		while (okToken) {
			if (buffer.isEmpty()) {
				try {
					buffer = ProductExchanger.exhanger.exchange(buffer);
					System.out.print("Consumer buffer:");
					for (int i : buffer) {
						System.out.print(i + ",");
					}
					System.out.println("\n");
					Thread.sleep((int) Math.random() * 1000);
					buffer.clear();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					okToken = false;
				}
			}
		}

	}

}
