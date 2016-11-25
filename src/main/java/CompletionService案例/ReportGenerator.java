package CompletionService案例;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * 
 * CompletionService案例在
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class ReportGenerator implements Callable<String> {
	private String sender;

	private String title;

	public ReportGenerator(String sender, String title) {
		super();
		this.sender = sender;
		this.title = title;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		CompletionService<String> service = new ExecutorCompletionService<>(executor);
		ReportRequest faceRequest = new ReportRequest("Face", service);
		ReportRequest onlineRequest = new ReportRequest("Online", service);
		Thread faceThread = new Thread(faceRequest);
		Thread onlineThread = new Thread(onlineRequest);
		ReportProcessor processor = new ReportProcessor(service);
		Thread senderThread = new Thread(processor);
		System.out.printf("Main: Starting the Threads\n");
		faceThread.start();
		onlineThread.start();
		senderThread.start();
		try {
			System.out.printf("Main: Waiting for the reportgenerators.");
			faceThread.join();
			onlineThread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Shutting down the executor.\n");
		executor.shutdown();//shutdown()方法关闭执行者
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);//awaitTermination()方法等待任务的结果
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		processor.setEnd(true);
		System.out.println("Main: Ends");

	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n", this.sender, this.title, duration);
			TimeUnit.SECONDS.sleep(duration);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		String ret = sender + ": " + title;
		return ret;

	}

}

/**
 * 这个类将模拟一些报告请求
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
class ReportRequest implements Runnable {
	private String name;

	private CompletionService<String> service;

	public ReportRequest(String name, CompletionService<String> service) {
		super();
		this.name = name;
		this.service = service;
	}

	@Override
	public void run() {
		ReportGenerator reportGenerator = new ReportGenerator(name, "Report");
		service.submit(reportGenerator);//将任务提交给CompletionService
	}

}
/**
 * 
 * 这个类将获取ReportGenerator任务的结果
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */

class ReportProcessor implements Runnable {
	private CompletionService<String> service;

	private boolean end;

	public ReportProcessor(CompletionService<String> service) {
		super();
		this.service = service;
		end = false;
	}

	@Override
	public void run() {
		while (!end) {
			try {
				Future<String> result = service.poll(20, TimeUnit.SECONDS);//调用CompletionService接口的poll()方法，获取CompletionService执行的下个已完成任务的Future对象
				if (result != null) {
					String report = result.get();
					System.out.printf("ReportReceiver: Report Received:%s\n", report);
				}
			}
			catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("ReportSender: End\n");
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

}