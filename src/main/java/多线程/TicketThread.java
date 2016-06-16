package 多线程;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月15日
 *
 * 作用:Runnable简单案例
 */
public class TicketThread implements Runnable{
	private int i;

 
	public TicketThread(int i) {
		super();
		this.i = i;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+"----"+i);
		}
		
	}
	public static void main(String[] args) {
/*		TicketThread t=new TicketThread(0);
		new Thread(t,"线程1").start();;
		new Thread(t,"线程2").start();;
		new Thread(t,"线程3").start();;*/
		Tickt t=new Tickt();
		new Thread(t,"线程1").start();;
		new Thread(t,"线程2").start();;
		
	}

}

class Tickt implements Runnable{
	private int j=5;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*synchronized (this) {
	
			if(i>0){
				  System.out.println(i);
				  i--;
			}
		}
		*/
		for(int i=0;i<1000;i++){
			synchronized (this) {
				if(j>0){
					System.out.println(j);
					j--;
				}
			}
			
			
		}
	}
 
	
}