 package redis.jedis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import redis.clients.jedis.Jedis;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月26日
 *
 * 作用:redis案例
 */
public class RedisJava {
	 public static void main(String[] args) {
		 connectRedis();
	 }
	 
	 /**
	  * redis案例
	  */
	 public static void connectRedis(){
		 //连接本地的 Redis 服务
	      Jedis jedis = new Jedis("127.0.0.1");
	     /* System.out.println("Connection to server sucessfully"+"---"+jedis.save());*/
	      //查看服务是否运行
	      System.out.println("Server is running: "+jedis.ping());
	      /**
	       * Redis Java String(字符串) 实例
	       */
	      //设置 redis 字符串数据
	      jedis.set("w3ckey", "Redis tutorial");
	     // 获取存储的数据并输出
	     System.out.println("Stored string in redis:: "+ jedis.get("w3ckey"));
	     /**
	      * Redis Java List(列表) 实例
	      */
	     //存储数据到列表中
	      jedis.lpush("tutorial-list", "Redis");
	      jedis.lpush("tutorial-list", "Mongodb");
	      jedis.lpush("tutorial-list", "Mysql");
	     // 获取存储的数据并输出
	     List<String> list = jedis.lrange("tutorial-list", 0 ,5);
	     for(int i=0; i<list.size(); i++) {
	       System.out.println("Stored string in redis:: "+list.get(i));
	     }
	     /**
	      * Redis Java Keys 实例
	      */
	     // 获取数据并输出
	     HashSet keyset =   (HashSet) jedis.keys("*");
	     Iterator it=   keyset.iterator();
	     while(it.hasNext()){
	    	 System.out.println(it.next());
	     }
	  
	    /* for(int i=0; i<keylist.size(); i++) {
	       System.out.println("List of stored keys:: "+list.get(i));
	     }*/
	     
	 }
}
