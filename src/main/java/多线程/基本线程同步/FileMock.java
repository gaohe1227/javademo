package 多线程.基本线程同步;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Condition案例.模拟文本文件的读取和写入 所
 * 有Condition对象都与锁有关，并且使用声明在Lock接口中的newCondition()方法来创建。使用condition做任何操作之前，
 * 你必须获取与这个condition相关的锁的控制。所以，condition的操作一定是在以调用Lock对象的lock()方法为开头，以调用相同
 * Lock对象的unlock()方法为结尾的代码块中。
 * 
 * 当一个线程在一个condition上调用await()方法时，它将自动释放锁的控制，所以其他线程可以获取这个锁的控制并开始执行相同操作，或者由同个锁保护的其他临界区。
 * 
 * 注释：当一个线程在一个condition上调用signal()或signallAll()方法，一个或者全部在这个condition上等待的线程将被唤醒。这并不能保证的使它们现在睡眠的条件现在是true，所以你必须在while循环内部调用await()方法。你不能离开这个循环，直到
 * condition为true。当condition为false，你必须再次调用 await()方法。
 * 
 * 你必须十分小心
 * ，在使用await()和signal()方法时。如果你在condition上调用await()方法而却没有在这个condition上调用signal()方法，这个线程将永远睡眠下去。
 * 
 * 在调用await()方法后，一个线程可以被中断的，所以当它正在睡眠时，你必须处理InterruptedException异常。
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class FileMock {
	private String content[];// 存储文件内容

	private int index;// 存储被检索到的模拟文件的行数

	public FileMock(int size, int length) {// 实现FileMock类的构造器，用随机字符初始化文件的内容
		content = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuilder buffer = new StringBuilder(length);
			for (int j = 0; j < length; j++) {
				int indice = (int) Math.random() * 255;
				buffer.append((char) indice);
			}
			content[i] = buffer.toString();
		}
		index = 0;
	}

	public boolean hasMoreLines() {// 实现hasMoreLines()方法，如果文件有更多的行来处理，则返回true，如果我们已经取到了模拟文件的尾部，则返回false
		return index < content.length;
	}

	public String getLine() {// 实现getLine()方法，返回index属性所确定的行数并增加其值
		if (this.hasMoreLines()) {
			System.out.println("Mock: " + (content.length - index));
			return content[index++];
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileMock mock = new FileMock(100, 10);
		Buffer buffer = new Buffer(20);
		Producer producer = new Producer(mock, buffer);
		Thread threadProducer = new Thread(producer, "Producer");
		Consumer consumers[] = new Consumer[3];
		Thread threadConsumers[] = new Thread[3];
		for (int i = 0; i < 3; i++) {
			consumers[i] = new Consumer(buffer);
			threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
		}
		threadProducer.start();
		for (int i = 0; i < 3; i++) {
			threadConsumers[i].start();
		}

	}

}

/**
 * 
 * 实现Buffer类，用来实现在生产者与消费者之间共享的缓冲区
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
class Buffer {
	private LinkedList<String> buffer;// 一个类型为LinkedList<String>，名为buffer的属性，用来存储共享数据

	private int maxSize;// 一个类型为int，名为maxSize的属性，用来存储缓冲区的长度

	private ReentrantLock reentrantLock;// 一个名为reentrantLock的ReentrantLock对象，用来控制修改缓冲区代码块的访问

	private Condition lines;

	private Condition space;

	private boolean pendingLines;// 一个Boolean类型，名为pendingLines的属性，表明如果缓冲区中有行

	public Buffer(int maxSize) {
		this.maxSize = maxSize;
		buffer = new LinkedList<>();
		reentrantLock = new ReentrantLock();
		lines = reentrantLock.newCondition();
		space = reentrantLock.newCondition();
		pendingLines = true;

	}

	/**
	 * 实现insert()方法，接收一个String类型参数并试图将它存储到缓冲区。首先，它获得锁的控制。当它有锁的控制，它将检查缓冲区是否有空闲空
	 * 间。如果缓冲区已满，它将调用await()方法在space条件上等待释放空间。如果其他线程在space条件上调用signal()或
	 * signalAll()方法，这个线程将被唤醒。当这种情况发生，这个线程在缓冲区上存储行并且在lines条件上调用signallAll()方法，稍
	 * 后我们将会看到，这个条件将会唤醒所有在缓冲行上等待的线程
	 * @param line
	 */
	public void insert(String line) {
		reentrantLock.lock();
		try {
			while (buffer.size() == maxSize) {

				space.await();// 线程等待
			}
			buffer.offer(line);
			System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
			lines.signalAll();// 唤醒线程

		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			reentrantLock.unlock();
		}
	}

	/**
	 * 实现get()方法，它返回存储在缓冲区上的第一个字符串。首先，它获取锁的控制。当它拥有锁的控制，它检查缓冲区是否有行。如果缓冲区是空的，它调用
	 * await()方法在lines条件上等待缓冲区中的行。如果其他线程在lines条件上调用signal()或signalAll()方法，这个线程将
	 * 被唤醒。当它发生时，这个方法获取缓冲区的首行，并且在space条件上调用signalAll()方法，然后返回String
	 * @return
	 */
	public String get() {
		String line = null;
		reentrantLock.lock();
		try {
			while ((buffer.size() == 0) && (hasPendingLines())) {
				lines.await();
			}
			if (hasPendingLines()) {
				line = buffer.poll();
				System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());

				space.signalAll();
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			reentrantLock.unlock();
		}
		return line;
	}

	public void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}

	public boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}

}

class Producer implements Runnable {
	private FileMock mock;

	private Buffer buffer;

	public Producer(FileMock mock, Buffer buffer) {
		super();
		this.mock = mock;
		this.buffer = buffer;
	}

	/**
	 * 实现run()方法，读取在FileMock对象中创建的所有行，并使用insert()方法将它们存储到缓冲区。一旦这个过程结束，使用setPendingLines()方法警告缓冲区，不会再产生更多的行
	 */
	@Override
	public void run() {
		buffer.setPendingLines(true);
		while (mock.hasMoreLines()) {
			String line = mock.getLine();
			buffer.insert(line);
		}
		buffer.setPendingLines(false);
	}

}

class Consumer implements Runnable {
	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (buffer.hasPendingLines()) {
			String line = buffer.get();
			processLine(line);
		}
	}

	private void processLine(String line) {
		try {
			Random random = new Random();
			Thread.sleep(random.nextInt(100));
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
