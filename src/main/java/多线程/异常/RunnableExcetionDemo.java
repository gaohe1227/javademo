package 多线程.异常;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 
 * 使用Runnable获取异常信息的案例
 * 也是在线程里处理不受控制的异常的案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月21日
 */
public class RunnableExcetionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new MyExceptionHandler());
		thread.start();
	}

}

class Task implements Runnable {
	@Override
	public void run() {
		int numero = Integer.parseInt("TTT");
	}
}

class MyExceptionHandler implements UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread: %s\n", t.getId());
		System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n", t.getState());
	}
}