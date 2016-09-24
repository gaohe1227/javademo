package 多线程;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
	public static void main(String[] args) {
		  CyclicBarrier barrier = new CyclicBarrier(3);  
		  
	        ExecutorService executor = Executors.newFixedThreadPool(3);  
	        executor.submit(new Thread(new Runner(barrier, "1号选手")));  
	        executor.submit(new Thread(new Runner(barrier, "2号选手")));  
	        //executor.submit(new Thread(new Runner(barrier, "3号选手")));  
	  
	        executor.shutdown();  
	}
}
class Runner implements Runnable{
    private CyclicBarrier cyclicBarrier; // 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point) 
    private String name;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {  
            Thread.sleep(1000 * (new Random()).nextInt(8));  
            System.out.println(name + " 准备好了...");  
            // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
            System.out.println(cyclicBarrier.getNumberWaiting());
            cyclicBarrier.await();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } catch (BrokenBarrierException e) {  
            e.printStackTrace();  
        }  
        System.out.println(name + " 起跑！");  
		
	}
	public Runner(CyclicBarrier cyclicBarrier, String name) {
		super();
		this.cyclicBarrier = cyclicBarrier;
		this.name = name;
	}
	
	
}