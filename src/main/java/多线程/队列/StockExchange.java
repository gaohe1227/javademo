package 多线程.队列;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * 基于阻塞队列的的股票交易系统(LinkedBlockingQueue案例)
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月14日
 */
public class StockExchange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.printf("Hit Enter to terminate%n%n");
		BlockingQueue<Integer> orderQueue = new LinkedBlockingQueue<Integer>();
		Saller saller = new Saller(orderQueue);// 创建卖家
		Thread[] sellerThread = new Thread[100];
		for (int i = 0; i < 100; i++) {
			sellerThread[i] = new Thread(saller);
			sellerThread[i].start();// 启动销售线程
		}
		Buyer buyer = new Buyer(orderQueue);
		Thread[] buyerThread = new Thread[100];
		for (int i = 0; i < 100; i++) {
			buyerThread[i] = new Thread(buyer);
			buyerThread[i].start();// 启动购买线程
		}
		try {
			while (System.in.read() != '\n') {
				System.err.println("Terminating-----------------------");
				saller.setShutDownRequest(true);
				buyer.setShutDownRequest(true);
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for (Thread thread : sellerThread) {
			thread.interrupt();
		}

		for (Thread thread : buyerThread) {
			thread.interrupt();
		}*/

	}

}

/**
 * 
 * 卖家
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月14日
 */
class Saller implements Runnable {
	private BlockingQueue orderQueu;// 订单队列

	private boolean shutDownRequest = false;// 阻塞状态太

	private static int id;

	public boolean isShutDownRequest() {
		return shutDownRequest;
	}

	public void setShutDownRequest(boolean shutDownRequest) {
		this.shutDownRequest = shutDownRequest;
	}

	public Saller(BlockingQueue orderQueu) {
		super();
		this.orderQueu = orderQueu;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (shutDownRequest == false) {
			Integer quantity = (int) (Math.random() * 100);
			try {
				orderQueu.put(quantity);
				System.out.println("Sell order by " + Thread.currentThread().getName() + ":" + quantity);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				shutDownRequest = true;
			}
		}

	}

}

class Buyer implements Runnable {
	private BlockingQueue orderQueu;// 订单队列

	private boolean shutDownRequest = false;// 阻塞状态
	 

	public boolean isShutDownRequest() {
		return shutDownRequest;
	}

	public void setShutDownRequest(boolean shutDownRequest) {
		this.shutDownRequest = shutDownRequest;
	}

	public Buyer(BlockingQueue orderQueu) {
		super();
		this.orderQueu = orderQueu;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (shutDownRequest == false) {
			try {
				Integer quantity = (Integer) orderQueu.take();
				System.out.println("Buy order by" + Thread.currentThread().getName() + ":" + quantity);

			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				shutDownRequest=true;
			}
		}

	}

}