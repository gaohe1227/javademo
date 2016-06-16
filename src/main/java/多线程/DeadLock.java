package 多线程;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月15日
 *
 * 作用:死锁案例
 */
public class DeadLock implements Runnable {
	private static Zhang z=new Zhang();
	private static Li l=new Li();
	private boolean flag=false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(flag){
			synchronized (z) {
				z.say();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (l) {
					z.get();
				}
			}
		}else{
			synchronized (l) {
				l.say();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (z) {
					l.get();
				}
			}
			
		}
		
	}
	public static void main(String[] args) {
		DeadLock d1=new DeadLock();
		DeadLock d2=new DeadLock();
		d1.flag=true;
		d2.flag=false;
		new Thread(d1).start();
		new Thread(d2).start();
	}

}

class Zhang{
	public void say(){
		System.out.println("李四你把书借给我");
	}
	public void get(){
		System.out.println("张三得到了李四的书");
	}
}
class Li{
	public void say(){
		System.out.println("张三你把画借给我");
	}
	public void get(){
		System.out.println("李四得到了张三的画");
	}
 
}
