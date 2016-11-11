package 多线程.forjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * ForkJoin案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月11日
 */
public class Fibonacci extends RecursiveTask<Long> {
	final long n;

	public Fibonacci(long n) {
		super();
		this.n = n;
	}

	static Long fibonacci(long n) {

		if (n <= 1) {
			return n;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	@Override
	protected Long compute() {// 操作compute()方法
		// TODO Auto-generated method stub

		if (this.n < 10) {// n<10,直接返回结果
			return fibonacci(n);
		}
		Fibonacci f1 = new Fibonacci(n - 1);// 分解出n-1个子任务
		f1.fork();// 请forkJoinPool分配线程来执行这个任务
		Fibonacci f2 = new Fibonacci(n - 2);// 分解出n-2个子任务
		return f2.compute() + f1.join();// f2.compute()代表直接执行这个任务;f1.join()代表取得任务f1的结果
	}// 继承RecursiveTask

	public static void main(String[] args) {
	    Long start=System.currentTimeMillis();
           Fibonacci fibonacci=new Fibonacci(45);
           ForkJoinPool forkJoinPool=new ForkJoinPool();
           System.out.println(forkJoinPool.invoke(fibonacci));
           
	    System.out.println(Fibonacci.fibonacci(45));
	    Long end=System.currentTimeMillis();
	    
           System.out.println(end-start);
	}
}
