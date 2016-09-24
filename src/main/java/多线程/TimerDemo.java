package 多线程;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author 高鹤
 *
 * 2016年7月14日
 *
 * 作用:Timer定时器案例
 */
public class TimerDemo {
public static void main(String[] args) {
	Timer timer=new Timer();//建立Timer类对象
	MyTask task=new MyTask();
	timer.schedule(task, 1000,2000);//设置人物的执行,1秒后开始，每2秒执行一次
}
}
/**
 * 
 * @author 高鹤
 *
 * 2016年7月14日
 *
 * 作用:TimerTask任务的具体实现类
 */
class MyTask  extends TimerTask{//具体实现

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("当前时间:"+new Date());
		
	}
	
}