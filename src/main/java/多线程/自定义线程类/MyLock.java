package 多线程.自定义线程类;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 
 * 自定义Lock类
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月24日
 */
public class MyLock implements Lock {
	private AbstractQueuedSynchronizer sync;

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		sync.acquire(1);

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		sync.acquireInterruptibly(1);

	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		try {
			return sync.tryAcquireNanos(1, 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub

		return sync.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		sync.release(1);

	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return sync.new ConditionObject();

	}

	public MyLock() {
		super();
		// TODO Auto-generated constructor stub
		sync = new MyAbstractQueuedSynchronizer();
	}

	public static void main(String args[]) {
		MyLock lock = new MyLock();
		for (int i = 0; i < 10; i++) {
			Task task = new Task(lock, "Task-" + i);
			Thread thread = new Thread(task);
			thread.start();
		}
		boolean value;
		do {
			try {
				value = lock.tryLock(1, TimeUnit.SECONDS);
				if (!value) {
					System.out.printf("Main: Trying to get the Lock\n");
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				value = false;
			}
		} while (!value);
		System.out.printf("Main: Got the lock\n");
		lock.unlock();
		System.out.printf("Main: End of the program\n");

	}

}

class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {
	private AtomicInteger state;// 状态

	public MyAbstractQueuedSynchronizer() {
		super();
		this.state = new AtomicInteger(0);
	}

	/**
	 * 实现tryAcquire方法,这个方法试图将state的值由0变1(如果成功，它将返回true，否则，返回false)
	 */
	@Override
	protected boolean tryAcquire(int arg) {
		// TODO Auto-generated method stub
		// return super.tryAcquire(arg);
		return state.compareAndSet(0, 1);
	}

	/**
	 * 实现tryRelease()方法。这个方法试图将变量sate的值从1变成0.如果成功，它将返回true，否则，返回false
	 */
	@Override
	protected boolean tryRelease(int arg) {
		// TODO Auto-generated method stub
		return state.compareAndSet(1, 0);
	}
}

class Task implements Runnable {
	private MyLock lock;

	private String name;

	public Task(MyLock lock, String name) {
		super();
		this.lock = lock;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.printf("Task: %s: Take the lock\n", name);
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.printf("Task: %s: Free the lock\n", name);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}

}