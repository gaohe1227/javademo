package cache.demo4;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author 高鹤
 *
 *  2016年6月24日
 *
 *  作用:简单缓存的实现（网上抄的）
 */
public class SimpleCache<K, V> {
	private final Lock lock = new ReentrantLock();
	private final int maxCapacity;
	private final Map<K, V> eden;
	private final Map<K, V> perm;

	/**
	 * 初始化缓存对象
	 * 
	 * @param maxCapacity
	 */
	public SimpleCache(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.eden = new ConcurrentHashMap<K, V>(maxCapacity);
		this.perm = new WeakHashMap<K, V>(maxCapacity);
	}
   /**
    * 从缓存中取出数据
    * @param k
    * @return
    */
	public V get(K k) {
		V v = this.eden.get(k);
		if (v == null) {
			lock.lock();
			try {
				v = this.perm.get(k);
			} finally {
				lock.unlock();
			}
			if (v != null) {
				this.eden.put(k, v);
			}
		}
		return v;
	}
    /**
     * 向缓存中添加数据
     * @param k
     * @param v
     */
	public void put(K k, V v) {
		if (this.eden.size() >= maxCapacity) {
			lock.lock();
			try {
				this.perm.putAll(this.eden);
			} finally {
				lock.unlock();
			}
			this.eden.clear();
		}
		this.eden.put(k, v);
	}
}
