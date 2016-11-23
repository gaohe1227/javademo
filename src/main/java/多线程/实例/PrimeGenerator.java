package 多线程.实例;
/**
 * 
 * 中断机制案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月17日
 */
public class PrimeGenerator extends Thread {
	public void run() {
		long number = 1L;
		while (true) {
			if (isPrime(number)) {
				System.out.println("Number " + number + " is Prime");

			}
			if (currentThread().isInterrupted()) {
				System.out.println("The Prime Generator has been Interrupted");
				return;
			}
			number++;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread task = new PrimeGenerator();
		task.start();
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();//发送令线程状态变为中断状态的请求送

	}

	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}

		for (long i = 2; i < number; i++) {
			if ((number % i) == 0) {
				return false;
			}
		}
		return true;
	}
}
