package 多线程.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
 

public class ThreadPool {
	private BlockingQueue taskQueue = null;
	  private List<PoolThread> threads = new ArrayList<PoolThread>();
	  private boolean isStopped = false;

	  public ThreadPool(int noOfThreads, int maxNoOfTasks) {
	    taskQueue = new LinkedBlockingQueue(maxNoOfTasks);

	    for (int i=0; i<noOfThreads; i++) {
	      threads.add(new PoolThread(taskQueue));
	    }
	    for (PoolThread thread : threads) {
	      thread.start();
	    }
	  }

	  public  synchronized void execute(Runnable task) throws InterruptedException {
	    if(this.isStopped) throw
	      new IllegalStateException("ThreadPool is stopped"); 
	    this.taskQueue.put(task);
	  }

	  public synchronized boolean stop() {
	    this.isStopped = true;
	    for (PoolThread thread : threads) {
	      thread.stop();
	    }
		return isStopped;
	  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
class PoolThread extends Thread {

	  private BlockingQueue<Runnable> taskQueue = null;
	  private boolean       isStopped = false;

	  public PoolThread(BlockingQueue<Runnable> queue) {
	    taskQueue = queue;
	  }

	  public void run() {
	    while (!isStopped()) {
	      try {
	        Runnable runnable =taskQueue.take();
	        runnable.run();
	      } catch(Exception e) {
	        // 写日志或者报告异常,
	        // 但保持线程池运行.
	      }
	    }
	  }

	  public synchronized void toStop() {
	    isStopped = true;
	    this.interrupt(); // 打断池中线程的 dequeue() 调用.
	  }

	  public synchronized boolean isStopped() {
	    return isStopped;
	  }
	}