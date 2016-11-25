package 多线程.生产者和消费者案例.许巧辉;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * 模仿生产者---消费者案例例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class EventStorage {
	private int maxSize;

	private List<Date> storage;

	public EventStorage() {
		maxSize = 10;
		storage = new LinkedList<>();
	}

	public synchronized void set() {
		while (storage.size() == maxSize) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		storage.add(new Date());
		System.out.printf("Set: %d", storage.size());
		notifyAll();
	}
	public synchronized void get(){
		while (storage.size()==0){
		try {
		wait();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
		System.out.printf("Get: %d: %s",storage.
		size(),((LinkedList<?>)storage).poll());
		notifyAll();
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventStorage storage=new EventStorage();
		Producer producer=new Producer(storage);
		Thread thread1=new Thread(producer);
		Consumer consumer=new Consumer(storage);
		Thread thread2=new Thread(consumer);
		thread1.start();
		thread2.start();

	}

}
class Producer implements Runnable {
	private EventStorage storage;
	public Producer(EventStorage storage){
	this.storage=storage;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i=0; i<100; i++){
			storage.set();
			}
	}
	
}
class Consumer implements Runnable{
	private EventStorage storage;
	public Consumer(EventStorage storage){
	this.storage=storage;
	}
	@Override
	public void run() {
		// TODfor (int i=0; i<100; i++){
		storage.get();
		}
	}
	
 
