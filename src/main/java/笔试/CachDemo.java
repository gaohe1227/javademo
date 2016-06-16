package 笔试;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月16日
 *
 * 作用:自定义缓存类的例子
 */
public class CachDemo {
	private Map<String,Object> cache=new HashMap();
	private  ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

	public CachDemo() {
		// TODO Auto-generated constructor stub
	}
	public Object getData(String key){
		readWriteLock.readLock().lock();
		Object value=null;
		try{
			value=cache.get(key);
			if(null==value){
				readWriteLock.readLock().unlock();
				readWriteLock.writeLock().lock();
				try{
					if(null==value){
						value="aaaa";
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					readWriteLock.writeLock().unlock();
				}
				readWriteLock.readLock().lock();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			readWriteLock.readLock().unlock();
		}
		return value;
	}

}
