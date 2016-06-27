package com.redis;

 import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;


/** 
 * jedis String 类型操作 
 * @author http://blog.csdn.net/java2000_wl 
 * @version <b>1.0</b> 
 */  
public class RedisStringTest {

	private Jedis jedis;
	
	/**
	 * 初始化连接
	 * <br>------------------------------<br>
	 */
	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1");
		//jedis.auth("java2000_wl");
	}
	
	/**
	 * 断开连接
	 * <br>------------------------------<br>
	 */
	@After
	public void after() {
		jedis.disconnect();
	}
	
	/**
	 * set 如果存在相同的key 覆盖旧值</br>
	 * 总是返回OK
	 * <br>------------------------------<br> 
	 */
	@Test
	public void testSet() {
		String result = jedis.set("blog", "java2000_wl");
		print(result);
		jedis.set("blog", "java2000_wl_new");
		print(result);
	}
	
	/**
	 * setnx 如果key存在 不做任何操作  返回0, </br>
	 * 		   如果key不存在  设置值成功 返回1
	 * <br>------------------------------<br> 
	 */
	@Test
	public void testSetnx() {
		long result = jedis.setnx("blog1", "java2000_wl");
		print(result);
	}
	
	/**
	 * setex 设置值  并指定键值对应的有效期  单位为秒</br>
	 * 如果key 存在 覆盖旧值  成功返回 OK
	 * <br>------------------------------<br> 
	 */
	@Test
	public void testSetex() {
		String result = jedis.setex("setex", 60, "setex_value");
		print(result);
	}
	
	/**
	 * setrange 通过key 和  offset 替换value </br>
	 * 例如:setex - > setex_value   
	 * jedis.setrange("setex", 6, "Setrange")
	 * </br>  
	 * 替换为 setex_Setrange
	 * <br>------------------------------<br> 
	 */
	@Test
	public void testSetrange() {
		// setex_value  被替换为: setex_Setrange
		long result = jedis.setrange("setex", 6, "Setrange");
		print(result);
		print(jedis.get("setex"));
	}
	
	/**
	 * mset 同时设置一个或多个 key-value对。
	 * 如果某个key-value 存在  会用新值覆盖原来的旧值, 总是成功， 成功返回OK
	 * <br>------------------------------<br>
	 */
	@Test
	public void testMset() {
		String result = jedis.mset("key1", "value1", "key2", "value2", "key..", "value..");
		print(result);
	}
	
	/**
	 * msetnx 同时设置一个或多个 key-value对。
	 * 如果某个key-value存在   返回0  所有操作都会回滚, 如果成功 返回ok
	 * <br>------------------------------<br>
	 */
	@Test
	public void testMsetnx() {
		long result = jedis.msetnx("msetnxKey1", "msetnxValue1", "msetnxKey2", "msetnxValue2");
		print(result);
	}
	
	/**
	 * get 通过key 获取对应的value  如果key不存在 返回nil
	 * <br>------------------------------<br>
	 */
	@Test
	public void testGet() {
		String value = jedis.get("msetnxKey1");
		print(value);
	}
	
	/**
	 * getset 通过key 获取对应的value 然后通过key 设置信的value
	 * <br>------------------------------<br>
	 */
	@Test
	public void testGetSet() {
		String value = jedis.getSet("key1", "value_new");
		print(value);
		
		value = jedis.get("key1");
		print(value);
	}
	
	/**
	 * 返回key对应的value  在由start 和 end 两个偏移量截取
	 * <br>------------------------------<br>
	 */
	@Test
	public void testGetrange() {
		String value = jedis.getrange("key1", 6, 8);
		print(value);
	}
	
	/**
	 * 返回多个key 对应的value
	 * <br>------------------------------<br>
	 */
	@Test
	public void testMget() {
		List<String> list = jedis.mget("key1", "key2", "key3");
		print(list);
	}
	
	/**
	 * 对key对应的value  做+1操作  返回+1后的新值
	 * <br>------------------------------<br>
	 */
	@Test
	public void testIncr() {
		jedis.set("incr", "2");
		// 返回33
		Long result = jedis.incr("incr");
		print(result);
	}
	
	/**
	 * 对key对应的value 加指定值 返回新值  如果key不存在 认为原来的value为0
	 * <br>------------------------------<br>
	 */
	@Test
	public void testIncrBy() {
		jedis.set("incrBy", "2");
		// 返回7
		Long result = jedis.incrBy("incrBy", 5);
		print(result);
	}
	
	/**
	 * 对key对应的value 做-1操作  返回新值
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDecr() {
		// 7 - 1
		Long result = jedis.decr("incrBy");
		print(result);
	}
	
	/**
	 * 对key对应的value 减去指定值  返回新值  如果key不存在    认为原来的value为0
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDecrBy() {
		Long result = jedis.decrBy("DecrBy", 5);
		print(result);
	}
	
	/**
	 * 给指定的key的值追加, 返回新字符串的长度
	 * <br>------------------------------<br>
	 */
	@Test
	public void testAppend() {
		// 追加前 value_new
		String old = jedis.get("key1");
		print(old);
		// 追加后  value_new124
		Long long1 =jedis.append("key1", "124");
		print(long1);
	}
	
	/**
	 * 取得指定key的value值的长度
	 * <br>------------------------------<br>
	 */
	@Test
	public void testStlen() {
		long len = jedis.strlen("key1");
		print(len);
	}
	
	void print(Object object) {
		System.out.println(object.toString());
	}
}
