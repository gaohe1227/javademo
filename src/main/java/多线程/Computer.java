package 多线程;

import java.util.concurrent.atomic.AtomicInteger;

public class Computer{
	public static void main(String[] args) {
		Computer f=new Computer();
		ComputerProducer p=new ComputerProducer(f);
		ComputerCustomer c=new ComputerCustomer(f);
		new Thread(p).start();
		new Thread(c).start();
	}
	private int j=0;
	 

	public synchronized void getI() {
	 
		if(j==0){
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("卖:"+j); 
	   super.notify();
	   j--;
	}

	public synchronized void setI(int i) {
		if(j>0){
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    j++;
		System.out.println("生产"+i);
		super.notify();
		 
	
	}
	
	
}
/**
 * 
 * @author 高鹤
 *
 * 2016年6月15日
 *
 * 作用:生产者
 */
class ComputerProducer implements Runnable{
 private Computer info;
	public ComputerProducer(Computer info) {
	super();
	this.info = info;
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	 
		/**
		 * 生产信息
		 */
		for(int i=0;i<50;i++){
			 this.info.setI(i+1);
			 
		}
		
	}
	
}
/**]
 * 
 * @author 高鹤
 *
 * 2016年6月15日
 *
 * 作用:消费者
 */
class ComputerCustomer implements Runnable{
	 private Computer info;
		public ComputerCustomer(Computer info) {
		super();
		this.info = info;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<50;i++){   
				 this.info.getI();//取出信息
		}
		
	}
	
}