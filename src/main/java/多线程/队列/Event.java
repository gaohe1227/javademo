package 多线程.队列;

import java.util.concurrent.PriorityBlockingQueue;
/**
 * 
 * PriorityBlockingQueue案例(可用于排序,PriorityBlockingQueue中的元素必须实现Comparable接口)
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class Event implements Comparable<Event> {
	private int thread;// 事件数

	private int priority;// 优先级

	public Event(int thread, int priority) {
		this.thread = thread;
		this.priority = priority;
	}

	public int getThread() {
		return thread;
	}

	public void setThread(int thread) {
		this.thread = thread;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		if (this.priority > o.getPriority()) {
			return -1;
		}
		else if (this.priority < o.getPriority()) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
		Thread taskThreads[] = new Thread[5];
		for (int i = 0; i < taskThreads.length; i++) {
			Task task = new Task(i, queue);

			taskThreads[i] = new Thread(task);
		}
		for (int i = 0; i < taskThreads.length; i++) {
			taskThreads[i].start();
		}
		for (int i = 0; i < taskThreads.length; i++) {
			try {
				taskThreads[i].join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: Queue Size: %d\n", queue.size());
		for (int i = 0; i < taskThreads.length * 1000; i++) {
			Event event = queue.poll();
			System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
		}
		System.out.printf("Main: Queue Size: %d\n", queue.size());
		System.out.printf("Main: End of the program\n");

	}

}

class Task implements Runnable {
	private int id;

	private PriorityBlockingQueue<Event> queue;

	public Task(int id, PriorityBlockingQueue<Event> queue) {
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 1000; i++) {
			Event event = new Event(id, i);
			queue.add(event);
		}
	}

}