package 多线程.线程池;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 延迟任务执行案例 ScheduledThreadPoolExecutor的chedule()方法，让执行者在一段时间后执行任务。这个方法接收3个参数:
 * 你想要执行的任务 你想要让任务在执行前等待多长时间 时间单位，指定为TimeUnit类的常数
 * 
 * 周期性执行任务可以使用ScheduledThreadPoolExecutor的scheduledAtFixedRate()方法此方法接收4个参数：你想要周期性执行的任务、第一次执行任务的延迟时间、两次执行之间的间隔期间、第2、3个参数的时间单位。它是TimeUnit类的常
 * 量，TimeUnit类是个枚举类，有如下常量：DAYS，HOURS，MICROSECONDS， MILLISECONDS，
 * MINUTES,，NANOSECONDS 和SECONDS。scheduleAtFixedRate()
 * 方法返回ScheduledFuture对象，它继承Future接口，这个方法和调度任务一起协同工作。ScheduledFuture是一个参数化接口（校对注：ScheduledFuture<V>）。
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月22日
 */
public class ScheduledThreadPoolExecutorDemo1 implements Callable<String> {
	private String name;

	public ScheduledThreadPoolExecutorDemo1(String name) {
		super();
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.printf("%s: Starting at : %s\n", name, new Date());
		return "Hello, world";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		System.out.printf("Main: Starting at: %s\n", new Date());
		for (int i = 0; i < 5; i++) {
			Task task = new Task("Task " + i);
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}
		executor.shutdown();

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Ends at: %s\n", new Date());

	}

}
