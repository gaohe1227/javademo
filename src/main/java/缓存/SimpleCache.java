package 缓存;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
 * 利用读写锁实现的一个简单缓存类
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月25日
 */
public class SimpleCache {
    private static Map<String,Object> map=new HashMap<String,Object>();
    static  ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    static Lock writeLock=readWriteLock.writeLock();
    static Lock readLock=readWriteLock.readLock();
    public static final Object get(String key) {
    	readLock.lock();
		try {
			return map.get(key);
		} finally {
			readLock.unlock();
		}
	}
	// 设置key对应的value，并返回旧有的value
	public static final Object put(String key, Object value) {
		writeLock.lock();
		try {
			return map.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}
	// 清空所有的内容
	public static final void clear() {
		writeLock.lock();
		try {
			map.clear();
		} finally {
			writeLock.unlock();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
