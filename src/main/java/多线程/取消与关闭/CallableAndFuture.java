package 多线程.取消与关闭;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 
 * @author 高鹤
 *
 * 2016年8月5日
 *
 * 作用:Callable、Future案例
 */
public class CallableAndFuture {
	/*public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    return taskID;
                }
            });
        }
        // 可能做一些事情
        for(int i = 1; i < 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }*/
	public static void main(String[] args) {
		 
        ExecutorService threadPool = Executors.newCachedThreadPool();
        /*CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);*/
        threadPool.submit(new  Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				 
					 for(int i=0;i<100;i++){
	        	System.out.println("子线程----"+i);
	        	
	            }
				 
				return 32;
			}
			 
		});
        for(int i=0;i<100;i++){
        	System.out.println("main----"+i);
        	
        }
/*        List<Future> futures=new ArrayList<Future>();
        for(int i = 0; i < 100; i++){
        
        	final Integer taskid=i;
        	 futures.add(threadPool.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					return taskid;
				}
			}));
        }
    
     // 可能做一些事情
        for(int i = 0; i < 100; i++) {
            try {
                System.out.println(futures.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();  */
       
        for(int i=0;i<100;i++){
        	System.out.println("j----"+i);
        	
        }
        threadPool.shutdown();
	}
}
