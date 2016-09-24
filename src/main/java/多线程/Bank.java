package 多线程;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @author 高鹤
 *
 * 2016年7月7日
 *
 * 作用:信号灯案例
 */
public class Bank {
	private final static int CONUT = 10;
	private final static Semaphore semaphore = new Semaphore(2, true);// 信号灯对象

	public static void main(String[] args) {
        for(int i=0;i<CONUT;i++){
        	final int count=i;
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						if(semaphore.tryAcquire(10, TimeUnit.MILLISECONDS)){
							Teller.getServer(count);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						semaphore.release();
					}
					
				}
			}).start();;
        }
	}
}
class Teller{
	static public void getServer(int i){
		try {
			System.out.println("severing------:"+i);
			Thread.sleep((long) (Math.random()*10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
