package 多线程.队列;

import java.util.LinkedList;
import java.util.List;
/**
 * '
 * 网上抄的一个阻塞队列实现
 * 必须注意到，在enqueue和dequeue方法内部，只有队列的大小等于上限（limit）或者下限（0）时，才调用notifyAll方法。如果队列的大小既不等于上限，
 * 也不等于下限，任何线程调用enqueue或者dequeue方法时，都不会阻塞，都能够正常的往队列中添加或者移除元素现
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月17日
 */
public class MyBlockingQueue {
	private List queue = new LinkedList();

	private int limit = 10;

	public MyBlockingQueue(int limit) {

		this.limit = limit;

	}

	public synchronized void enqueue(Object item)

			throws InterruptedException {

		while (this.queue.size() == this.limit) {

			wait();

		}

		if (this.queue.size() == 0) {

			notifyAll();

		}

		this.queue.add(item);

	}

	public synchronized Object dequeue()

			throws InterruptedException {

		while (this.queue.size() == 0) {

			wait();

		}

		if (this.queue.size() == this.limit) {

			notifyAll();

		}

		return this.queue.remove(0);

	}
}
