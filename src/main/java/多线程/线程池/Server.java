package 多线程.线程池;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 
 * 缓存线程池简单案例(Executors.newCachedThreadPool()会创建一个ExecutorService对象)
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月22日
 */
public class Server {
	private ThreadPoolExecutor executor;

	public Server() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();//创建一个缓存线程池
	}

	public void executeTask(Task task) {
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);//提交任务等待执行
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());//输出线程池的大小
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());//输出线程池中的线程数
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());//输出完成任务数
		System.err.printf("Server: Completed Tasks: %d\n", executor.getTaskCount());//提交给线程池的任务数
	}

	public void endServer() {
		executor.shutdown();//关闭线程池
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		for (int i = 0; i < 100; i++) {
			Task task = new Task("Task " + i);
			server.executeTask(task);
		}
		server.endServer();
	}

}

class Task implements Runnable {
	private Date initDate;

	private String name;

	public Task(String name) {
		initDate = new Date();
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Task %s: Doing a task during %dseconds\n", Thread.currentThread().getName(), name, duration);
			TimeUnit.SECONDS.sleep(duration);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
	}

}