package 多线程.实例;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourcesLoader implements Runnable {
	@Override
	public void run() {
		 
		System.out.println("Beginning data sources loading-----------------" + new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Beginning data sources loading-----------------" + new Date()); 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader);
		System.out.println(new Date());
		T1 t1=new T1(thread1);
		Thread thread2 = new Thread(t1);
		thread1.start();
		thread2.start();
		/*try {
			//thread1.join();
			thread2.join();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=----------------end");
	}

}

class T1  implements Runnable{
	Thread t1;
	public T1(Thread t1) {
		super();
		this.t1 = t1;
	}
	@Override
	public void run() {
		 
		System.out.println("Beginning data sources loading----------------我是T1-" + new Date());
		try {
			t1.join();
			TimeUnit.SECONDS.sleep(4);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Beginning data sources loading---------我是T1--------" + new Date()); 
	}
}
