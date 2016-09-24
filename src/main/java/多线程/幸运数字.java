package 多线程;

import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * 
 * @author 高鹤
 *
 * 2016年7月7日
 *
 * 作用:幸运数字案例
 */

public class 幸运数字 {
	public static void main(String[] args) {
		TransferQueue<String> queue=new LinkedTransferQueue<String>();
		Thread p=new Thread(new ProducerLuckNumber(queue));
		p.setDaemon(true);
		p.start();
		for (int i = 0; i < 100; i++) {
			Thread c=new Thread(new CustomerLuckNumber(queue));
			c.setDaemon(true);
			c.start();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
/**
 * 
 * @author 高鹤
 *
 * 2016年7月7日
 *
 * 作用:TransferQueue的接口中的案例
 */
class ProducerLuckNumber implements Runnable {

	private final TransferQueue<String> queue;

	private String getNumber() {
		return "你的幸运数字:"+(new Random().nextInt(100));
	}

 
	public ProducerLuckNumber(TransferQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				if(queue.hasWaitingConsumer()){
					queue.transfer(getNumber());
				}
			}
			
		}catch(InterruptedException er){
			
		}

	}

}
class CustomerLuckNumber implements Runnable{
	private final TransferQueue<String> queue;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			 System.out.println("客户"+Thread.currentThread().getName()+"的幸运数字为:"+queue.take());
			
		}catch(InterruptedException er){
			
		}
	}
	public CustomerLuckNumber(TransferQueue<String> queue) {
		super();
		this.queue = queue;
	}
	
}