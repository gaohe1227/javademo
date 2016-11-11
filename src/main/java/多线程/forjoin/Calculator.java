package 多线程.forjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.apache.log4j.chainsaw.Main;

public class Calculator extends RecursiveTask<Integer> {

	private static final int THRESHOLD = 100;  
    private int start;  
    private int end;  
  
    public Calculator(int start, int end) {  
        this.start = start;  
        this.end = end;  
    }  
  
    @Override  
    protected Integer compute() {  
        int sum = 0;
     /*   for(int i = start; i< end;i++){  
            sum += i;  
             
        }  */
        if((start - end) < THRESHOLD){  
        	
            for(int i = start; i< end;i++){  
                sum += i;  
                 
            }  
        }else{  
            int middle = (start + end) /2;  
            Calculator left = new Calculator(start, middle);  
            Calculator right = new Calculator(middle + 1, end);  
            left.fork();  
            right.fork();  
  
            sum = left.join() + right.join();  
        }  
        return sum;  
    }  
    public static void main(String srgs[]){
    	Calculator calculator=new Calculator(0,10000);
    	  ForkJoinPool forkJoinPool=new ForkJoinPool();
    	  System.out.println(forkJoinPool.invoke(calculator));
    }

}
