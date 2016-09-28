package redis案例;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import model.BackupFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author 高鹤
 *
 * 2016年9月28日
 *
 * 作用：
 */
public class JedisPoolDemo {
	static JedisPool 	pool ;
	static void initPool(){
		ResourceBundle bundle = ResourceBundle.getBundle("redis");//获取redis.properties
		if (bundle == null) {
			System.err.println("-------------");
			throw new IllegalArgumentException(
			"[redis.properties] is not found!");
			}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow"))); 
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		pool = new JedisPool(config, bundle.getString("redis.ip"),Integer.valueOf(bundle.getString("redis.port"))); 
	}
	
	public static void main(String[] args) {
		initPool();
		// 从池中获取一个Jedis对象
		// 从池中获取一个Jedis对象
		Jedis jedis = pool.getResource();
		String keys = "name1";

		// 删数据
		jedis.del(keys);
		// 存数据
		jedis.set(keys, "snowolf");
		// 取数据
		String value = jedis.get(keys);

		System.out.println(value);

		// 释放对象池
		pool.returnResource(jedis);
		//改为从对象池中，获取Jedis实例：
		 
		Jedis jedis1 = pool.getResource();
		String keys1 = "name1";

		// 删数据
		jedis1.del(keys);
		// 存数据
		jedis1.set(keys, "snowolf");
		// 取数据yushangying
		String value1 = jedis1.get(keys);

		System.out.println(value1);
		  value1 = jedis1.get("yushangying");

		System.out.println(value1);

		// 释放对象池
		pool.returnResource(jedis);
		//改为从对象池中，获取Jedis实例：
		BackupFile backupFile=new BackupFile();
		backupFile.setFiletype("img");
		backupFile.setName("测试");
		backupFile.setSize(1024);
		backupFile.setTime(new Date());
		Jedis jedis2 = pool.getResource();
		jedis2.sadd(UUID.randomUUID().toString(), backupFile.toString());
		/*String keys2 = "name1"; 
		// 存数据
		jedis1.set(keys2, "snowolf");
		// 取数据yushangying
		String value2 = jedis1.get(keys2); 
		System.out.println(value2); */
		// 释放对象池
		pool.returnResource(jedis);
 jedis2 = pool.getResource();
		jedis2.set(UUID.randomUUID().toString().getBytes(), backupFile.toString().getBytes());
	 
		// 释放对象池
		pool.returnResource(jedis2);
	
	}
}
