package jdk8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
/**
 * 
 * @author 高鹤
 *
 * 2016年9月23日
 *
 * 作用：for/join案例
 */
public class Forjoindemo {
	public static void main(String[] args) {
 
		Long start=System.currentTimeMillis();
		  ForkJoinPool forkJoinPool = new ForkJoinPool();//对线程池的扩展
	        Future<Integer> result = forkJoinPool.submit(new Demo1(1, 1000000)); 
	        try {
				System.out.println(result.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        forkJoinPool.shutdown();
	        Long end=System.currentTimeMillis();
	        System.out.println(end-start);
	 
	    
	}
}
class Demo1 extends RecursiveTask<Integer> {
    private int start;
    private int end;

    public Demo1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    //计算
    @Override
    protected Integer compute() {
        int sum = 0;
        if (start - end < 100) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {//间隔有100则拆分多个任务计算
            int middle = (start + end) / 2;
            Demo1 left = new Demo1(start, middle);
            Demo1 right = new Demo1(middle + 1, end);
            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }
        return sum;
    }
}