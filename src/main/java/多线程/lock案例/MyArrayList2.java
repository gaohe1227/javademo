package 多线程.lock案例;

import java.util.Arrays;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
 * ReadWriteLock简单案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月11日
 */
public class MyArrayList2 {
	private ReadWriteLock lock=new ReentrantReadWriteLock();//使用读写锁
	private Object[] list;
	private int next;
	
	public MyArrayList2() {
		 
		// TODO Auto-generated constructor stub
		this(16);
	}
	 
	public MyArrayList2(int initialCapacity) {
		 
		// TODO Auto-generated constructor stub
		list=new Object[initialCapacity];
	}
	public void add(Object o){
		try{
			lock.writeLock().lock();//取得写入锁定
			if(next==list.length){
				list=Arrays.copyOf(list,list.length*2);
			}
			list[next++]=o;
		}finally{
			lock.writeLock().unlock();//解除写入锁定
		}
	}
	public int size() {
		// TODO Auto-generated method stub
		try {
			lock.readLock().lock();
			
		return next; 
		}finally{
			lock.readLock().unlock();
		}
	}

	public Object get(int index) {
		// TODO Auto-generated method stub
		try {
			lock.readLock().lock();
		return list[index];
		}finally{
			lock.readLock().unlock();
		}
	}

}
