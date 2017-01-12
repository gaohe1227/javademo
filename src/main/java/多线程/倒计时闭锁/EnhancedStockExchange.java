package 多线程.倒计时闭锁;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

 
/**
 * 
 * 倒计时计数器案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月15日
 */
public class EnhancedStockExchange {
	public static void main(String[] args) {
		BlockingQueue<Integer> orderQueue = new LinkedBlockingQueue<Integer>();
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch stopSignal = new CountDownLatch(200);
		Seller seller = new Seller(orderQueue, startSignal, stopSignal);
		Thread[] sellerThread=new Thread[100];
		for(int i=0;i<100;i++){
			sellerThread[i]=new Thread(seller);
			sellerThread[i].start();
		}
		Buyer buyer = new Buyer(orderQueue, startSignal, stopSignal);
		Thread[] buyerThread=new Thread[100];
		for(int i=0;i<100;i++){
			buyerThread[i]=new Thread(buyer);
			buyerThread[i].start();
		}
		System.out.println("go--开始");
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		startSignal.countDown();
		try {
			while(System.in.read()!='\n'){//输入Enter键
				System.out.println("终止---------------------------");
				seller.setShutDownRequest(true);
				buyer.setShutDownRequest(true);
				try {
					stopSignal.await();
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
/**
 * 
 * 卖家
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月15日
 */
class Seller implements Runnable {
	private BlockingQueue<Integer> orderQueue;

	private boolean shutDownRequest = false;

	private CountDownLatch startSignal, stopSignal;

	public Seller(BlockingQueue<Integer> orderQueue, CountDownLatch startSignal, CountDownLatch stopSignal) {
		super();
		this.orderQueue = orderQueue;
		this.startSignal = startSignal;
		this.stopSignal = stopSignal;
	}

	public boolean isShutDownRequest() {
		return shutDownRequest;
	}

	public void setShutDownRequest(boolean shutDownRequest) {
		this.shutDownRequest = shutDownRequest;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			startSignal.await();//等待
			System.out.println(Thread.currentThread().getName()+"开始执行");
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (shutDownRequest == false) {
			Integer quality = (int) (Math.random() * 100);
			try {
				orderQueue.put(quality);
				System.out.println("Sell order producer # " + Thread.currentThread().getName() + ":" + quality);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				shutDownRequest=true;
			}
		}
		stopSignal.countDown();
		System.err.println("Seller 结束");
	}

}
/**
 * 
 * 买家
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月15日
 */
class Buyer implements Runnable{
	private BlockingQueue<Integer> orderQueue;

	private boolean shutDownRequest = false;

	private CountDownLatch startSignal, stopSignal;

	public Buyer(BlockingQueue<Integer> orderQueue, CountDownLatch startSignal, CountDownLatch stopSignal) {
		super();
		this.orderQueue = orderQueue;
		this.startSignal = startSignal;
		this.stopSignal = stopSignal;
	}

	public void setShutDownRequest(boolean b) {
		// TODO Auto-generated method stub
		this.shutDownRequest=b;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			startSignal.await();//等待
			System.out.println(Thread.currentThread().getName()+"开始执行");
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (shutDownRequest == false) {
			
			try {
				Integer quality = orderQueue.take();
				System.out.println("Buy order consumer # " + Thread.currentThread().getName() + ":" + quality);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				shutDownRequest=true;
			}
		}
		stopSignal.countDown();
		System.err.println("buyer 结束");
		
	}
	
}