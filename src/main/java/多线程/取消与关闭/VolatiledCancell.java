package 多线程.取消与关闭;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.notification.RunListener.ThreadSafe;
/**
 * 
 * @author 高鹤
 *
 * 2016年8月2日
 *
 * 作用:使用Volatiled取消任务
 */
@ThreadSafe
public class VolatiledCancell implements Runnable {

	private final List<BigInteger> primes = new ArrayList<BigInteger>();
	private volatile boolean cancelled;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BigInteger p = BigInteger.ONE;//素数
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {

				System.out.println("--"+p);
				primes.add(p);
			}
		}

	}

	public void cancelled() {
		cancelled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}

	List<BigInteger> asSecondOfPrimes() throws InterruptedException {
		VolatiledCancell generator = new VolatiledCancell();
		new Thread(generator).start();
		try {
			Thread.sleep(5);
		} finally {
			generator.cancelled();
		}
		return generator.get();
	}

	public static void main(String[] args) {
		try {
			List<BigInteger> list=	new VolatiledCancell().asSecondOfPrimes();
			 System.out.println(list.size());
		for (BigInteger bigInteger : list) {
			System.out.println(bigInteger);
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
