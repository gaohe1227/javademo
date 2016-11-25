package 多线程.forjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 当你在ForkJoinPool中执行ForkJoinTask时，你可以使用同步或异步方式来实现。当你使用同步方式时，提交任务给池的方法直到提交的任务完成它的执行，才会返回结果。当你使用异步方式时，提交任务给执行者的方法将立即返回，所以这个任务可以继续执行。
 * 
 * 你应该意识到这两个方法有很大的区别，当你使用同步方法，调用这些方法（比如：invokeAll()方法）的任务将被阻塞，直到提交给池的任务完成它的执行。这允许ForkJoinPool类使用work-stealing算法，分配一个新的任务给正在执行睡眠任务的工作线程。
 * 反之，当你使用异步方法（比如：fork()方法），这个任务将继续它的执行，所以ForkJoinPool类不能使用work-stealing算法来提高应用程序的性能。在这种情况下，只有当你调用join()或get()方法来等待任务的完成时，ForkJoinPool才能使用work-stealing算法。
 * 
 *使用isCompletedAbnormally()方法，检查这个任务或它的子任务是否已经抛出异常。在这种情况下，将抛出的异常写入到控制台。使用ForkJoinTask类的getException()方法获取那个异常。
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class Calculator extends RecursiveTask<Integer> {

	private static final int THRESHOLD = 100;

	private int start;

	private int end;

	public Calculator(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		/*
		 * for(int i = start; i< end;i++){ sum += i;
		 * 
		 * }
		 */
		if ((start - end) < THRESHOLD) {

			for (int i = start; i < end; i++) {
				sum += i;

			}
		}
		else {
			int middle = (start + end) / 2;
			Calculator left = new Calculator(start, middle);
			Calculator right = new Calculator(middle + 1, end);
			left.fork(); // 执行
			right.fork();

			sum = left.join() + right.join();// 取值
		}
		return sum;
	}

	public static void main(String srgs[]) {
		Calculator calculator = new Calculator(0, 10000);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		System.out.println(forkJoinPool.invoke(calculator));
	}

}
