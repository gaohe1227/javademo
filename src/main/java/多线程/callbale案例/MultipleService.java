package 多线程.callbale案例;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月15日
 */
public class MultipleService {
     public static class Exp implements Callable<Double>{
        private double m;
        private int n;
        
		public Exp(double m, int n) {
			super();
			this.m = m;
			this.n = n;
		}

		@Override
		public Double call() {
			double result =1;
			for(int i=0;i<n;i++){
				result*=m;
				try {
					Thread.sleep(10);
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.printf("%nComputed %.02f raised to %d\n",m,n);
			return result;
			// TODO Auto-generated method stub
			
		}
    	 
     }
 
 
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executorService=Executors.newFixedThreadPool(10);
		ArrayList<Callable<Double>> tasks=new ArrayList<Callable<Double>>();
		for(int i=0;i<10;i++){
			double m=Math.random()*10;
			int n=(int)(Math.random()*1000);
			tasks.add(new Exp(m, n));
		}
		ExecutorCompletionService service=new ExecutorCompletionService<>(executorService);
		for (Callable<Double> task:tasks) {
			service.submit(task);
		}
		Lock lock=new ReentrantLock();
		for(int i=0;i<tasks.size();i++){
			
			try {
				lock.lock();
				Double d;
				d = (Double) service.take().get();
				System.out.printf("Result %E%n",d);
				 
			}
			catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
			
			
		}
		executorService.shutdown();

	}

}
