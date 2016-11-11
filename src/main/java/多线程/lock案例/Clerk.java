package 多线程.lock案例;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
 * Condition简单案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月11日
 */
public class Clerk {
	private int products = -1;

	private Lock lock = new ReentrantLock();

	private Condition condition = lock.newCondition();// 建立Condition对象

	public void setProducts(int products) throws InterruptedException {
		try {
			lock.lock();
			while (this.products != -1) {
				condition.await();// 用Condition的await()取代Object的wait()
			}
			this.products = products;
			System.out.println("生产者设定:--" + products);
			condition.signal();// 用Condition的Signal()取代Object的notify();
		}
		finally {
			lock.unlock();
		}
	}

	public int getPRoducts() throws InterruptedException {
		try {
			lock.lock();
			while (this.products == -1) {
				condition.await();
			}
			int p = this.products;
			System.out.println("消费者取走---" + this.products);
			this.products = -1;
			condition.signal();
			return this.products;
		}
		finally {
			lock.unlock();
		}
	}

}
