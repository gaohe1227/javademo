package ���߳�;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**\
 * 
 * @author �ߺ�
 *
 * 2016��6��16��
 *
 * ����:Timer����
 */
public class TestTimer {
	public TestTimer(int second){
		Timer timer=new Timer();
		timer.schedule(new MyTimerTask(), 0, second);
	}
   private static class MyTimerTask extends TimerTask{
	   public void run(){
		   System.out.println("��ǰʱ��Ϊ:"+new Date());
	   }
   }
   public static void main(String[] args) {
	new TestTimer(1000);
}
}
