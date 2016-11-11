package 多线程.lock案例;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
 * Lock接口案例1:
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月11日
 */
public class MyArrayList  {
	private Lock lock = new ReentrantLock();// 使用ReentrantLock创建对象
	private Object[] list;
	private int next;
	public MyArrayList() {
		//super();
		this(16);
		// TODO Auto-generated constructor stub
	}
	 
	public MyArrayList(int initialCapacity) {
	 
		// TODO Auto-generated constructor stub
		list=new Object[initialCapacity];
	}

 
	public int size() {
		// TODO Auto-generated method stub
		try {
			lock.lock();
		return next; 
		}finally{
			lock.unlock();
		}
	}

	public Object get(int index) {
		// TODO Auto-generated method stub
		try {
		lock.lock();
		return list[index];
		}finally{
			lock.unlock();
		}
	}

 
	public void add(Object e) {
		// TODO Auto-generated method stub
		/*return super.add(e);*/
		try {
			lock.lock();//进行锁定
			if(next==list.length){
				list=Arrays.copyOf(list, list.length*2);
			}
			list[next++]=e;
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			lock.unlock();//解除锁定
		}
			
	}
}
