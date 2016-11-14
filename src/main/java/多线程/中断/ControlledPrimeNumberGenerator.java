package 多线程.中断;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ControlledPrimeNumberGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread primeNumberGenerator=new Thread(new WorkerThread());
		primeNumberGenerator.start();
		InputStreamReader in=new InputStreamReader(System.in);
		try {
			while(in.read()!='\n'){
				
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		primeNumberGenerator.interrupt();
		if(primeNumberGenerator.isInterrupted()){
			System.out.println("\nNumber Genenrator has already been interrupted");
		}else{
			System.out.println("Number Genenrator is not correctly Running");
		}
		Thread lazyWorker=new Thread(new LazyWorker());
		lazyWorker.start();
		System.out.println("  Running Lazy Worker");
		try {
			Thread.sleep(000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lazyWorker.interrupted();
	}

}
class WorkerThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long i=1;
		while(true){
			long j;
			for(j=2;j<i;j++){
				long n=i%j;
				if(n==0){
					break;
				}
			}
			if(i==j){
				System.out.println(" "+i);
			}
			i++;
			if(Thread.interrupted()){
				System.out.println("\nStopping prime"+"number generator");
				break;
			}
		}

	}

}
class LazyWorker implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(100);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
/*			e.printStackTrace();*/
			System.out.println("Lazy Worker:"+e.toString());
		}
		
		
	}
	
}