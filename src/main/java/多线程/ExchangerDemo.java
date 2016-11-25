package 多线程;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Exchanger案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月24日
 */
public class ExchangerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 16. 创建2个buffers。分别给producer和consumer使用.
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();

		// 17. 创建Exchanger对象，用来同步producer和consumer。
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

		// 18. 创建Producer对象和Consumer对象。
		ExchangerProducer producer = new ExchangerProducer(buffer1, exchanger);
		ExchangerConsumer consumer = new ExchangerConsumer(buffer2, exchanger);

		// 19. 创建线程来执行producer和consumer并开始线程。
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
	}

}

class ExchangerProducer implements Runnable {
	private List<String> buffer;// 声明 List<String>对象，名为 buffer。这是等等要被相互交换的数据类型

	private final Exchanger<List<String>> exchanger;// 声明
													// Exchanger<List<String>>;
													// 对象，名为exchanger。这个
													// exchanger
													// 对象是用来同步producer和consumer的。

	public ExchangerProducer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Producer: Cycle %d\n", cycle);

			// 6. 在每次循环中，加10个字符串到buffer。
			for (int j = 0; j < 10; j++) {
				String message = "Event " + ((i * 10) + j);
				System.out.printf("Producer: %s\n", message);
				buffer.add(message);
			}

			// 7. 调用 exchange() 方法来与consumer交换数据。此方法可能会抛出InterruptedException
			// 异常, 加上处理代码。
			try {
				buffer = exchanger.exchange(buffer);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Producer: " + buffer.size());
			cycle++;
		}
	}

}

class ExchangerConsumer implements Runnable {
	private List<String> buffer;// 声明 List<String>对象，名为 buffer。这是等等要被相互交换的数据类型

	private final Exchanger<List<String>> exchanger;// 声明
													// Exchanger<List<String>>;
													// 对象，名为exchanger。这个
													// exchanger
													// 对象是用来同步producer和consumer的。

	public ExchangerConsumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Consumer: Cycle %d\n", cycle);

			// 13.
			// 在每次循环，首先调用exchange()方法来与producer同步。Consumer需要消耗数据。此方法可能会抛出InterruptedException异常,
			// 加上处理代码。
			try {
				buffer = exchanger.exchange(buffer);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 14.
			// 把producer发来的在buffer里的10字符串写到操控台并从buffer内删除，留空。System.out.println("Consumer:
			// " + buffer.size());
			for (int j = 0; j < 10; j++) {
				String message = buffer.get(0);
				System.out.println("Consumer: " + message);
				buffer.remove(0);
			}
			cycle++;
		}
	}
}