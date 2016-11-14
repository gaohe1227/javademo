package 多线程.信号量;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Bank {
	private static final int COUNT = 100;
 private  final static Semaphore SEMAPHORE=new Semaphore(2,true);
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<COUNT;i++){
			final int count=i;
			new Thread(){
				public void run(){
					try {
						if(SEMAPHORE.tryAcquire(10, TimeUnit.MILLISECONDS)){
							Saller.getService(count);
						}
					}
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						SEMAPHORE.release();
					}
				}
			}.start();
		}

	}

}

class Saller {
	public static void getService(int i) {
		System.out.println("serving: " + i);
		try {
			Thread.sleep((long) (Math.random() * 100));
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}