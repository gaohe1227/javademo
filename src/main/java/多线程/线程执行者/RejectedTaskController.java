package 多线程.线程执行者;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 执行者控制被拒绝的任务
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月24日
 */
public class RejectedTaskController implements RejectedExecutionHandler {

	/* (non-Javadoc)
	 * @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
	 */
	/**
	 * r:存储被拒绝的任务;executor:存储执行者
	 */
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// TODO Auto-generated method stub
		System.err.println("name: The task "+r.toString()+"  has been rejected\n");
		System.err.printf("RejectedTaskController: %s\n",executor.toString());
		System.err.printf("RejectedTaskController: Terminating:%s\n",executor.isTerminating());
		System.err.printf("RejectedTaksController: Terminated:%s\n",executor.isTerminated());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RejectedTaskController controller=new RejectedTaskController();
		ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
		executor.setRejectedExecutionHandler(controller);//建立执行者的控制器

		System.out.printf("Main: Starting.\n");
		for (int i=0; i<3; i++) {
		Task task=new Task("Task"+i);
		executor.submit(task);
		}
		System.out.printf("Main: Shutting down the Executor.\n");
		executor.shutdown();
		System.out.printf("Main: Sending another Task.\n");
		Task task=new Task("RejectedTask11");
		executor.submit(task);
	 
		System.out.println("Main: End");
		System.out.printf("Main: End.\n");

	}

}
class Task implements Runnable{
	private String name;

	public Task(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Task "+name+": Starting");
		try {
			long duration=(long)(Math.random()*10);
			System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds\n",name,duration);
			TimeUnit.SECONDS.sleep(duration);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}

		System.out.printf("Task %s: Ending\n",name);

	}
	public String toString() {
		return name;
		}

}