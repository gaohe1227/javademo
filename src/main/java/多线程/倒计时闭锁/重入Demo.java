package 多线程.倒计时闭锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 重入Demo {
    Lock lock=new ReentrantLock();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new 重入Demo().outer();
	}
	
	public   void outer(){
		lock.lock();
		System.out.println("-----outer");
		inner();
	}

	public   void inner() {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.println("inner");
	 
	}

}
