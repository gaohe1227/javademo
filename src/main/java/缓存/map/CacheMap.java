package 缓存.map;

import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 用来存储短暂对象的缓存类，实现Map接口，内部有一个定时器用来清除过期（30秒）的对象。
 * 为避免创建过多线程，没有特殊要求请使用getDefault()方法来获取本类的实例。
 * @author 高鹤
 *
 * 2016年8月3日
 *
 * 作用:网上抄的一个自定义缓存类
 */
public class CacheMap<K, V> extends AbstractMap<K, V> {

 
	private static final long DEFAULT_TIMEOUT =30* 60000;//默认30分钟
	private static CacheMap<Object, Object> defaultInstance;//缓存对象
	 
	private Map<K, CacheEntry> map = new HashMap<K, CacheEntry>();
	public static synchronized final CacheMap<Object, Object> getDefault() { 
		
		if(defaultInstance==null){
			synchronized (CacheMap.class) {
				if(defaultInstance==null)
				defaultInstance = new CacheMap<Object, Object>(DEFAULT_TIMEOUT);
			}
		}
		
		return defaultInstance;
	}
	private long cacheTimeout;//缓存时间
	private CacheMap(long timeout) {
		this.cacheTimeout = timeout;
		new ClearThread().start();//启动清理map超时数据的线程
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> entrySet = new HashSet<Map.Entry<K, V>>();
		Set<Entry<K, CacheEntry>> wrapEntrySet = map.entrySet();
		for (Entry<K, CacheEntry> entry : wrapEntrySet) {
			entrySet.add(entry.getValue());
		}
		return entrySet;	
	}
	private class CacheEntry implements Entry<K, V> {
		long time;//缓存时间
		V value;
		K key;

		CacheEntry(K key, V value) {
			super();
			this.key = key;
			this.value = value; 
			this.time = System.currentTimeMillis();
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			return this.value = value;
		}
	}
	@Override
	public V get(Object key) {//获取数据
		CacheEntry entry = map.get(key);
		return entry == null ? null : entry.value;
	}
    
	@Override
	public V put(K key, V value) {//放入数据
		CacheEntry entry = new CacheEntry(key, value);
		synchronized (map) {
			map.put(key, entry);
		}
		return value;
	}
	private class ClearThread extends Thread {//清除缓存
		ClearThread() {
			setName("clear cache thread");
		}

		public void run() {
			while (true) {
				System.out.println(new Date());
				try {
					long now = System.currentTimeMillis();
					Object[] keys = map.keySet().toArray();
					for (Object key : keys) {
						CacheEntry entry = map.get(key);
						if (now - entry.time >= cacheTimeout) {
							synchronized (map) {
								map.remove(key);
								System.out.println("移除"+key);
							}
						}
					}
					Thread.sleep(cacheTimeout);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
 

}
