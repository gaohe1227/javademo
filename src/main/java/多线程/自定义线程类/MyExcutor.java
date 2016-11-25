package 多线程.自定义线程类;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 自定义线程执行者
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月24日
 */
public class MyExcutor extends ThreadPoolExecutor {//ThreadPoolExecutor是一个可以构建自带线程池的任务执行器
	private ConcurrentHashMap<String, Date> startTimes;

	public MyExcutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);// 调用父类构造器,初始化一个线程池型
		// TODO Auto-generated constructor stub
		startTimes = new ConcurrentHashMap<String, Date>();// 初始化startTimes

	}

	/**
	 * 覆盖shutdown()方法。将关于已执行的任务，正在运行的任务和待处理的任务信息写入到控制台。然后，使用super关键字调用父类的shutdown()方法
	 */
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		/* super.shutdown(); */
		System.err.println("MyExcutor-----shutdown()");
		System.out.println("MyExcutor: Executed tasks:" + getCompletedTaskCount());// 输出完成的任务数
		System.out.println("MyExcutor: Executed tasks:" + getActiveCount());// 输出正在运行的任务数
		System.out.println("MyExcutor: Executed tasks:" + getQueue().size());// 输出队列的大小
		super.shutdown();// 调用父类的关闭方法.调用shutDown()方法之后,执行者不会立即结束,但如果在调用了shutDown()之后,你试图提交其他任务
                   		 //给执行者,它将会拒绝,并且抛出RejectedExecutionException异常
	}

	/**
	 * 覆盖shutdownNow()方法。将关于已执行的任务，正在运行的任务和待处理的任务信息写入到控制台。然后，使用super关键字调用父类的shutdownNow()方法
	 */
	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		/* super.shutdown(); */
		System.err.println("MyExcutor-----shutdowNow()");
		System.out.println("MyExcutor: Executed tasks:" + getCompletedTaskCount());// 输出完成的任务数
		System.out.println("MyExcutor: Executed tasks:" + getActiveCount());// 输出正在运行的任务数
		System.out.println("MyExcutor: Executed tasks:" + getQueue().size());// 输出队列的大小
		return super.shutdownNow();// 调用父类的关闭方法
	}

	/**
	 * 在执行任务之前调用.写入一条信息（将要执行任务的线程名和任务的哈希编码）到控制台.在HashMap中,使用这个任务的希编码作为key，存储开始日期
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		// TODO Auto-generated method stub
		/* super.beforeExecute(t, r); */
		System.err.println("beforeExecute------------"+t.getName() + "-----------------" + r.hashCode());
		startTimes.put(String.valueOf(r.hashCode()), new Date());

	}

	/**
	 * 覆盖afterExecute()方法。将任务的结果和计算任务的运行时间（将当前时间减去存储在HashMap中的任务的开始时间）的信息写入到控制台
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		// TODO Auto-generated method stub
		/* super.afterExecute(r, t); */
		Future<?> result = (Future<?>) r;
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("MyExecutor:--- A task isfinishing:");
		System.out.println("MyExecutor:-----result:");
		try {
			System.out.println(result.get());
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date startDate = startTimes.get(String.valueOf(r.hashCode()));// 获取开始时间
		Date finishDate = new Date();
		/*System.out.println(startDate+"-----------------------"+finishDate);*/
		long diff = startDate.getTime() - finishDate.getTime();// 运行时间
		System.out.println("MyExecutor:Duration:" + diff);
		System.out.println("------------------------------------------------------------------------------------");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyExcutor myExcutor = new MyExcutor(2, 2, 1000, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());
		List<Future<String>> results = new ArrayList<Future<String>>();// 创建一个参数化为String类的Future对象的数列，用于存储你将提交给执行者的任务的结果对象
		for (int i = 0; i < 10; i++) {
			SleepTwoSecondsTask task = new SleepTwoSecondsTask();
			Future<String> result = myExcutor.submit(task);// 提交任务
			results.add(result);
		}
		for (int i = 0; i < 5; i++) {
			try {
				String result = results.get(i).get();// 使用get()方法，获取前5个任务的执行结果。将这些信息写入到控制台。
				System.out.println("Main: Result for Task1-5 " + i + "------------" + result);
			}
			catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		myExcutor.shutdown();// 关闭这个执行者
		for (int i = 5; i < 10; i++) {// 使用get()方法，获取后5个任务的执行结果。将这些信息写入到控制台
			try {
				String result = results.get(i).get();
				System.out.println("Main: Result for Task6-10 " + i + "------------" + result);
			}
			catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		try {
			myExcutor.awaitTermination(1, TimeUnit.DAYS);// 使用awaitTermination()方法等待这个执行者完成所有任务或超时
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	 
		myExcutor.shutdown();// 关闭这个执行者.它不会立即关闭,只是发送关闭的请求
		for (int i = 5; i < 10; i++) {// 使用get()方法，获取后5个任务的执行结果。将这些信息写入到控制台
			try {
				String result = results.get(i).get();
				System.out.println("Main: Result for Task " + i + "------------" + result);
			}
			catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		try {
			myExcutor.awaitTermination(1, TimeUnit.DAYS);// 使用awaitTermination()方法等待这个执行者的完成
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program.\n");

	}

}

class SleepTwoSecondsTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		TimeUnit.SECONDS.sleep(2);
		return new Date().toString();
	}

}
