package 多线程.队列;

import java.util.concurrent.ThreadLocalRandom;

 

/**
 * 
 * ThreadLocalRandom生成随机数案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class TaskLocalRandom implements Runnable {

	public TaskLocalRandom() {
		super();
		// TODO Auto-generated constructor stub
		ThreadLocalRandom.current();//使用current()方法给实际线程初始化随机数生成器
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String name=Thread.currentThread().getName();
		for (int i=0; i<10; i++){
			System.out.printf("%s: %d\n",name,ThreadLocalRandom.current().nextInt(10));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread threads[]=new Thread[3];
		for (int i=0; i<3; i++) {
			TaskLocalRandom task=new TaskLocalRandom();
			threads[i]=new Thread(task);
			threads[i].start();
		}
	}

}
