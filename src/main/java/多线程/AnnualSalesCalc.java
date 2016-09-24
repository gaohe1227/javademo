package 多线程;

import java.text.DateFormatSymbols;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.ibatis.executor.ExecutorException;

public class AnnualSalesCalc {
	private static int NUMBER_OF_CUSTOMER = 100;
	private static int NUMBER_OF_MONTHS = 12;
	private static int salesMatrix[][];

	private static class Summer implements Callable<Integer> {
		private int companyID;

		public Summer(int companyID) {
			super();
			this.companyID = companyID;
		}

		@Override
		public Integer call() {
			// TODO Auto-generated method stub
			int sum = 0;
			for (int col = 0; col < NUMBER_OF_MONTHS; col++) {
				sum += salesMatrix[companyID][col];
			}
			System.out.printf("Totaaling is:" + sum);
			return sum;
		}

	}

	private static void generateMatris() {
		salesMatrix = new int[NUMBER_OF_CUSTOMER][NUMBER_OF_MONTHS];
		for (int i = 0; i < NUMBER_OF_CUSTOMER; i++) {
			for (int j = 0; j < NUMBER_OF_MONTHS; j++) {
				salesMatrix[i][j] = (int) (Math.random() * 100);

			}
		}
	}

	private static void printMatris() {
		System.out.print("\t\t");
		String[] monthDisplayNames = (new DateFormatSymbols()).getShortMonths();//月份数组
		for (String strName : monthDisplayNames) {
			System.out.printf("%8s", strName);
		}
		System.out.printf("%n%n");
		for (int i = 0; i < NUMBER_OF_CUSTOMER; i++) {
			System.out.printf("Clint ID:1%02d", i);
			for (int j = 0; j < NUMBER_OF_MONTHS; j++) {
				System.out.printf("%8d", salesMatrix[i][j]);
			}
			System.out.println();
		}
		System.out.printf("%n%n");
	}

	public static void main(String[] args) {
		generateMatris();
		printMatris();
		ExecutorService executor=Executors.newFixedThreadPool(10);
		Set<Future<Integer>>  set=new HashSet<Future<Integer>>();
		for(int row=0;row<NUMBER_OF_CUSTOMER;row++){
			Callable<Integer> callable=new Summer(row);
			Future<Integer> future=executor.submit(callable);
			set.add(future); 
		}
		int sum=0;
		for(Future<Integer> future: set){
			try {
				sum+=future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		System.err.println("\n"+sum);
		executor.shutdown();
	}
}
