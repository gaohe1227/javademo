package 多线程.生产者和消费者案例.许巧辉;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo {
	private double price1;

	private double price2;

	private ReadWriteLock readWriteLock;// 读写锁

	public PricesInfo() {
		price1 = 1.0;
		price2 = 2.0;
		readWriteLock = new ReentrantReadWriteLock();// 实例化读写锁对象象
	}

	/**
	 * 获取jia
	 * @return
	 */
	public double getPrice1() {
		readWriteLock.readLock().lock();
		double value = price1;
		readWriteLock.readLock().unlock();
		return value;
	}

	public double getPrice2() {
		readWriteLock.readLock().lock();
		double value = price2;
		readWriteLock.readLock().unlock();
		return value;
	}

	public void setPrices(double price1, double price2) {
		readWriteLock.writeLock().lock();
		this.price1 = price1;
		this.price2 = price2;
		readWriteLock.writeLock().unlock();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PricesInfo pricesInfo = new PricesInfo();
		Reader readers[] = new Reader[5];
		Thread threadsReader[] = new Thread[5];
		for (int i = 0; i < 5; i++) {
			readers[i] = new Reader(pricesInfo);
			threadsReader[i] = new Thread(readers[i]);
		}
		Writer writer = new Writer(pricesInfo);
		Thread threadWriter = new Thread(writer);
		for (int i = 0; i < 5; i++) {
			threadsReader[i].start();
		}
		threadWriter.start();

	}

}

class Reader implements Runnable {
	private PricesInfo pricesInfo;

	public Reader(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
			System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
		}
	}

}

class Writer implements Runnable {
	private PricesInfo pricesInfo;

	public Writer(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.printf("Writer: Attempt to modify theprices.\n");
			pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
			System.out.printf("Writer: Prices have been modified.\n");
			try {
				Thread.sleep(2);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}