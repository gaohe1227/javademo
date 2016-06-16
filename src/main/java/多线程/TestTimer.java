package 多线程;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**\
 * 
 * @author 高鹤
 *
 * 2016年6月16日
 *
 * 作用:Timer案例
 */
public class TestTimer {
	public TestTimer(int second){
		Timer timer=new Timer();
		timer.schedule(new MyTimerTask(), 0, second);
	}
   private static class MyTimerTask extends TimerTask{
	   public void run(){
		   System.out.println("当前时间为:"+new Date());
	   }
   }
   public static void main(String[] args) {
	new TestTimer(1000);
}
}
