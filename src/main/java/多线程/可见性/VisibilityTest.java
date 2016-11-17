package 多线程.可见性;

public class VisibilityTest {
	public static void main(String args[]) throws InterruptedException {
		VisibilityThread v = new VisibilityThread();
		v.start();

		Thread.sleep(1000);//停顿1秒等待新启线程执行
		System.out.println("即将置stop值为true");
		v.stopIt();
		Thread.sleep(1000);
		System.out.println("finish main");
		System.out.println("main中通过getStop获取的stop值:" + v.getStop());
	}
}
class VisibilityThread extends Thread {
	private boolean stop;

	public void run() {
		int i = 0;
		System.out.println("start loop.");
		while(!getStop()) {
			i++;
		}
		System.out.println("finish loop,i=" + i+"----------"+getStop());
	}

	public void stopIt() {
		stop = true;
	}

	public boolean getStop(){
		return stop;
	}
}