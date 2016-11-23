package 多线程.callbale案例;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/**
 * 
 * FutureTask案例1
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class ExecutableTask implements Callable<String> {
	private String name;

	public String getName() {
		return name;
	}

	public ExecutableTask(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		}
		catch (InterruptedException e) {
		}
		return "Hello, world. I'm " + name;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		ResultTask resultTasks[] = new ResultTask[10];
		for (int i = 0; i < 10; i++) {
			ExecutableTask executableTask = new ExecutableTask("Task " + i);
			resultTasks[i] = new ResultTask(executableTask);
			executor.submit(resultTasks[i]);// 提交任务
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	/*	try {
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
		for (int i = 0; i < resultTasks.length; i++) {
			resultTasks[i].cancel(true);// 取消任务
		}
		for (int i = 0; i < resultTasks.length; i++) {

			if (!resultTasks[i].isCancelled()) {
				try {
					System.out.printf("%s\n", resultTasks[i].get());
				}
				catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		executor.shutdown();
	}

}

class ResultTask extends FutureTask<String> {
	private String name;

	public ResultTask(Callable<String> callable) {
		super(callable);
		// TODO Auto-generated constructor stub
		this.name = ((ExecutableTask) callable).getName();
	}
   /**
    * 执行者执行任务完成后执行一些代码
    */
	@Override
	protected void done() {
		if (isCancelled()) {
			System.out.printf("%s: Has been canceled\n", name);
		}
		else {
			System.out.printf("%s: Has finished\n", name);
		}
	}

}
