package 多线程.forjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class SumDemo extends RecursiveTask<Integer>{
     int start=0;
     int end=0;
     
	public SumDemo(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SumDemo sumDemo=new SumDemo(0, 1000);
		ForkJoinPool forkJoinPool=new ForkJoinPool();
		ForkJoinTask<Integer> sum=	forkJoinPool.submit(sumDemo);
		try {
			System.out.println(sum.get());
		}
		catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
     private int sum(int start,int end){
    	 return start+end;
     }
	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		int sum=0;
		if(end-start<10){
			for(int i=start;i<=end;i++){
				sum+=i;
			}
			return sum;
		}else{
			int middle=(start+end)/2;
			SumDemo leftSumDemo=new SumDemo(start, middle);
			SumDemo rightSumDemo=new SumDemo(middle, end);
			leftSumDemo.fork();
			rightSumDemo.fork();
			return leftSumDemo.join()+rightSumDemo.join();
			
		}
		 
	}

}
