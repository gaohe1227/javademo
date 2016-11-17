package 多线程.实例;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
/**
 * 
 * PipedStream管道案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月17日
 */
public class PipedTest {
	static class Print implements Runnable {
		private PipedInputStream in;

		public Print(PipedInputStream in) {
			this.in = in;
		}

		@Override
		public void run() {
			int receive = 0;
			try {
				while ((receive = in.read()) != -1) {
					System.out.println(receive);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		PipedOutputStream out = new PipedOutputStream();
		PipedInputStream in = new PipedInputStream();

		// Out ==> In
		out.connect(in);

		Thread t = new Thread(new Print(in));
		t.start();

		int receive = 0;

		while ((receive = System.in.read()) != -1) {
			out.write(receive);
		}
	}
}
