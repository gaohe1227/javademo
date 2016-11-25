package 多线程.自定义线程类;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
/**
 * 
 * 线程工厂实例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月22日
 */
public class MyThreadFactory implements ThreadFactory {
	private int counter;

	private String name;

	private List<String> stats;

	public MyThreadFactory(String name) {
		counter = 0;
		this.name = name;
		stats = new ArrayList<String>();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
		MyTask task = new MyTask();
		Thread thread;
		System.out.printf("Starting the Threads\n");
		for (int i = 0; i < 10; i++) {
			thread = factory.newThread(task);
			thread.start();
		}
		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", factory.getStats());
	}

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r, name + "-Thread_" + counter);
		counter++;
		stats.add(String.format("created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));

		return t;
	}

	public String getStats() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();
		while (it.hasNext()) {
			buffer.append(it.next());
			buffer.append("\n");
		}
		return buffer.toString();
	}
}

class MyTask implements Runnable {
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
