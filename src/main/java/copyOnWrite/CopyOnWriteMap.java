package copyOnWrite;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * copyOnwrite思想在Map上的实现（抄写自方 腾飞）
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月16日
 */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4115455425098796254L;

	private volatile Map<K, V> internalMap;

	public CopyOnWriteMap() {
		internalMap = new HashMap<K, V>();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return internalMap.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return internalMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return internalMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return internalMap.containsValue(value);
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return internalMap.get(key);// 取值为旧值
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		synchronized (this) {

			Map<K, V> newMap = new HashMap<K, V>(internalMap);// 创建一个新map

			V val = newMap.put(key, value);

			internalMap = newMap;

			return val;

		}

	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return internalMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> newData) {
		// TODO Auto-generated method stub
		synchronized (this) {

			Map<K, V> newMap = new HashMap<K, V>(internalMap);

			newMap.putAll(newData);

			internalMap = newMap;

		}

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		internalMap.clear();
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return internalMap.keySet();
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return internalMap.values();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return internalMap.entrySet();
	}

}
