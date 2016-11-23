package 多线程.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 
 * Callable简单案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月22日
 */
public class FactorialCalculator implements Callable<Integer> {
	private Integer number;

	public FactorialCalculator(Integer number) {
		super();
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int result = 1;
		if ((number == 0) || (number == 1)) {
			result = 1;
		}
		else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);
		return result;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}
		do {
			System.out.printf("Main: Number of Completed Tasks:%d\n", executor.getCompletedTaskCount());// 输出线程池完成的任务数
			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);// 获取Future对象，
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());// 使用isDone()方法，将信息写入（到控制台）表明它们所管理的任务是否已经完成
				try {
					TimeUnit.MILLISECONDS.sleep(50);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		} while (executor.getCompletedTaskCount() < resultList.size());//如果执行者中的已完成任务数小于10，重复这个循环
		System.out.printf("Main: Results\n");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);

			Integer number = null;
			try {
				number = result.get();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			catch (ExecutionException e) {
				e.printStackTrace();
			}
			

		}
		executor.shutdown();
	}
}
