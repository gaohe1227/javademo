package 多线程.死锁;
/**
 * 
 * 死锁案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月25日
 */
public class DeadLockDemo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		new Thread(new Runnable() {
			public void run() {
				alphonse.bow(gaston);
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				gaston.bow(alphonse);
			}
		}).start();
	}

}

class Friend {
	private final String name;

	public Friend(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void bow(Friend bower) {
		/*
		 * System.out.format("%s: %s" + "  has bowed to me!%n", this.name,
		 * bower.getName());
		 */
		System.out.println(this.name + "," + bower.getName() + "正在向我鞠躬");
		bower.bowBack(this);//重入
	}

	public synchronized void bowBack(Friend bower) {
		System.out.format("%s: %s" + " has bowed back to me!%n", this.name, bower.getName());
	}
}
