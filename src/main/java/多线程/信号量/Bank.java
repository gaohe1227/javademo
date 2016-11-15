package 多线程.信号量;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
 * 
 * 信号量案例(信号量的典型应用应为多个线程以某种方式竞争数量有限的资源) 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月14日
 */
public class Bank {
   private	static int j=1;
	private static final int COUNT = 100;//访问银行的客户数目

	private final static Semaphore SEMAPHORE = new Semaphore(2, true);//true代表公平设置,先进先出鸟

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < COUNT; i++) {
			final int count = i;
			new Thread() {
				public void run() {
					try {
						if (SEMAPHORE.tryAcquire(10, TimeUnit.MILLISECONDS)) {//SEMAPHORE.tryAcquire(10, TimeUnit.MILLISECONDS)中第1个参数指定等待的时间,第二个参数指定时间单位
							Saller.getService(count);
							System.out.println(j++);
						}
					}
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						SEMAPHORE.release();//释放信号许可
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