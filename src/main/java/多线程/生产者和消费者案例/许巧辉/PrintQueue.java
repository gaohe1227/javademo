
package 多线程.生产者和消费者案例.许巧辉;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Lock案例：Lock接口（和ReentrantLock类）包含其他方法来获取锁的控制权，那就是tryLock()方法。这个方法与lock()方法的最大区别是，如果一
 * 个线程调用这个方法不能获取Lock接口的控制权时，将会立即返回并且不会使这个线程进入睡眠。这个方法返回一个boolean值，true表示这个线程 获取了锁的控制权，
 * false则表示没有。注意：考虑到这个方法的结果，并采取相应的措施，这是程序员的责任。如果这个方法返回false值，预计你的程序不会执行这个临界区。
 * 如果是这样，你可能会在你的应用程序中得到错误的结果。ReentrantLock类也允许递归调用（锁的可重入性，译者注），当一个线程有锁的控制权并且使用递归调用，
 * 它延续了锁的控制权，所以调用lock()方法将会立即返回并且继续递归调用的执行。此外，我们也可以调用其他方法。
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class PrintQueue {
	private final Lock queueLock = new ReentrantLock();// 声明一个Lock对象，并且使用ReentrantLock类的一个新对象来初始化它

	/**
	 * 模拟文档的打印
	 * 
	 */
	public void printJob(Object document) {
		queueLock.lock();// 通过调用lock()方法来获取Lock对象的控制权
		Long duration = (long) (Math.random() * 10000);
		System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + "seconds");
		try {
			Thread.sleep(duration);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			queueLock.unlock();// 通过调用unlock()方法来释放Lock对象的控制
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintQueue printQueue = new PrintQueue();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread " + i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}

	}

}

class Job implements Runnable {
	private PrintQueue printQueue;

	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
	}

}
