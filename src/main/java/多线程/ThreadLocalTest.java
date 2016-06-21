package 多线程;

import java.util.Random;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月21日
 *
 * 作用:ThreadLocal案例
 */
public class ThreadLocalTest {
	//static Map<Thread,Integer> dataMap=new HashMap();
   static ThreadLocal<Integer> dataMap=new ThreadLocal<Integer>();
  
	public ThreadLocalTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		for( int i=0;i<2;i++){
			new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
				   int	data=new Random().nextInt(100);
					/*dataMap.put(Thread.currentThread(), data);*/
				   
					System.out.println(Thread.currentThread().getName()+"--------------------"+"---------"+data);
					dataMap.set(data);
					 new A().get();
					 new B().get();
					
				}
			}).start();
			
		}
		
	}
	static class A{
		public void get(){
			/*int data=dataMap.get(Thread.currentThread());*/
			int data=dataMap.get();
			System.out.println(Thread.currentThread().getName()+"----"+data+"--A");
		}
	}
	static class B{
		public void get(){
			//int data=dataMap.get(Thread.currentThread());
			int data=dataMap.get();
			System.out.println(Thread.currentThread().getName()+"----"+data+"--B");
		}
	}

}
