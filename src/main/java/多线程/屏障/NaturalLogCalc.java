package 多线程.屏障;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
 
/**
 * 
 * CyclicBarrier案例(只有所有成员都到达屏障，才能进行下一步操作,屏障是线程在等待另一个线程)
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月14日
 */
public class NaturalLogCalc {
	private static final int numberOfTerms = 10;

	private static double[] termArray = new double[numberOfTerms];

	private static final float x = 0.2f;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CyclicBarrier cyclicBarrier=new CyclicBarrier(numberOfTerms,new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Computing series sum");
				double sum=0;
				for (double d : termArray) {
					sum+=d;
				}
				System.out.println("ln(1-)"+x+") equals"+-sum);
			}
		});
		for (int i = 0; i < termArray.length; i++) {
			new Thread(new TermCalc(i, cyclicBarrier)).start();
		}
          System.out.println("waiting ------------------------------------");
	}

	private static class TermCalc implements Runnable {
		private int termIndex;

		private CyclicBarrier cyclicBarrier;

		public TermCalc(int termIndex, CyclicBarrier cyclicBarrier) {
			super();
			this.termIndex = termIndex;
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			double result=Math.pow(x, termIndex+1)/(termIndex+1);
			termArray[termIndex]=result;
			System.out.println("Term "+(termIndex+1)+": "+result);
			try {
				cyclicBarrier.await();
			}
			catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
