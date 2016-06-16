package 多线程;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月15日
 *
 * 作用:县线程状态:Thread t=new Thread(new MyThread(),"测试线程")：创建;t.start():开启就绪;
 *            t.join():挂起;t的run()执行时:运行状态;stop()或run方法执行完毕：线程死亡
 */
public class MyThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
	//当前线程休眠1秒钟
		for(int i=0;i<=100;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==50){
				 
				Thread.currentThread().interrupt();//终结当前线程的阻塞状态
			}
			System.out.println(Thread.currentThread().getName()+"----"+i);
		}
	}
public static void main(String[] args) {
	Thread t=new Thread(new MyThread(),"测试线程");
	t.start();
	for(int i=0;i<50;i++){
		 
		if(i==10){
			try {
				t.join();//线程强制执行
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("主线程:"+i);
	}
	
}
}
