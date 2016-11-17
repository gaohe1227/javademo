package 多线程.forjoin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {
	private static final int THRESHOLD = 2;// 阀值

	private int start;

	private int end;

	public CountTask(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		// return null;
		int sum = 0;
		boolean canCompute = (end - start) < THRESHOLD;
		if (canCompute) {//如果任务足够小就计算任务
			for (int i = start; i <= end; i++) {

				sum += i;

			}
		}else{//如果任务大于阀值，就分裂成两个子任务计算
			int middle=(end + start)/2;
			System.out.println(end +"--------------------------------"+ start);
			CountTask countTask1=new CountTask(start, middle);
			CountTask countTask2=new CountTask(middle, end);
			countTask1.fork();//执行子任务
			countTask2.fork();//执行子任务
			sum=countTask1.join()+countTask2.join();//获取子任务结果并想加
		}
		return sum;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool forkJoinPool=new ForkJoinPool();//创建线程池
		CountTask countTask=new CountTask(1, 100);
		Future<Integer> future=forkJoinPool.submit(countTask);//执行任务
		System.out.println(future.get());
		 
	 
		
	}

}
