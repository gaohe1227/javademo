package 多线程;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import org.junit.Test;

import 多线程.forjoin.Calculator;

public class ForJoinTest {
	@Test  
	public void run() throws Exception{  
		
		Long start=System.currentTimeMillis();
	 
	    ForkJoinPool forkJoinPool = new ForkJoinPool();  
	    Future<Integer> result = forkJoinPool.submit(new Calculator(0, 1000000000));  
	  
	/*    assertEquals(new Integer(49995000), result.get());  */
	    System.out.println(result.get());
		Long end=System.currentTimeMillis();
	    System.out.println(start+"---"+end+"----"+(end-start));
	}  
}
