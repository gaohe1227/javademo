package 缓存.ehcach;

 
import java.io.InputStream;
 

 
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

/**
 * 
 * @author 高鹤
 *
 * 2016年6月24日
 *
 * 作用:Ehcache案例
 */
public class EhcacheTest {
	public static void main(String[] args) {
		/*demo1();*/
		demo2();
		System.out.println("--------------------------------------------------");
		System.out.println(System.getProperty("user.dir")+"------------------"+System.getProperty("user.home")+"----"+System.getProperty("java.io.tmpdir"));
	}
   /**
    * 测试案例1
    */
	public static void demo1() {
		try {
			// 加载EhCache配置文件
			InputStream in = EhcacheTest.class.getClassLoader().getResourceAsStream("/cachconfig/ehcache.xml");
			CacheManager cm = CacheManager.create(in);

			// 列出所有的缓存名称，不包括配置文件中的<defaultCache>
			String[] names = cm.getCacheNames();//
			for (String s : names) {
				System.err.println(s);
			}

			// 只有一个名称为data-cache的cache
		 
			// 根据指定名称查找缓存对象
			Cache cache = cm.getCache("data-cache"); // 根据缓存名称获取缓存
			System.err.println(names.length+"--------"+cache); 
			

			// 获取，更新Cache配置的接口
			 CacheConfiguration configuration = cache.getCacheConfiguration();
			 configuration.setTimeToIdleSeconds(3600);//设置数据钝化时间

			// 缓存在内存中的配置信息，缓存配置动态修改也会体现出来,
			 System.err.println(cm.getActiveConfigurationText());

			// 清除所有缓存的数据，但是缓存本身仍然存在
			 cm.clearAll();

			// 从内存中删除一个缓存以及所有的数据，Cache被销毁
			 cm.removeCache("data-cache");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	   /**
	    * 测试案例2
	    */
		public static void demo2() {
			try {
				 //指定ehcache.xml的位置  
		          String fileName="E:/ehcache.xml";  
		          CacheManager manager = new CacheManager(fileName);  //创建cache管理器
					 
		          //列出所有的缓存名称，不包括配置文件中的<defaultCache>
		          String names[] = manager.getCacheNames();  
		          for(int i=0;i<names.length;i++){  
		              System.out.println(names[i]);  
		          }  
		          //根据cacheName生成一个Cache对象  
		          //第一种方式：  
		          Cache cache=manager.getCache(names[0]);  
		            
		          //第二种方式，ehcache里必须有defaultCache存在,"test"可以换成任何值  
		    /*    Cache cache1 = new Cache("test", 1, true, false, 5, 2);     
		        manager.addCache(cache1);  */ 
		            
		          //向Cache对象里添加Element元素，Element元素有key，value键值对组成  
		          cache.put(new Element("key1","values1"));  
		          Element element = cache.get("key1");  
		            
		          System.out.println(element.getValue());  
		          Object obj = element.getObjectValue();  
		          System.out.println((String)obj);  
		          manager.shutdown();  
		/*	 
				
		 
                  //设置一个名为test 的新cache,test属性为默认
				
				//cm.addCache("test");
				//设置一个名为test 的新cache,并定义其属性 
				Cache cache1 = new Cache("test", 1, true, false, 5, 2);  
				cm.addCache(cache1); 
				 //往cache中加入元素(从cache中取得元素)
				Element element = new Element("key1", "value1");  
				cache1.put(element);
				names = cm.getCacheNames();//
				for (String s : names) {
					System.err.println(s);
				}
				
				
				
				
				
				// 只有一个名称为data-cache的cache
			 
				// 根据指定名称查找缓存对象
				Cache cache = cm.getCache("data-cache"); // 根据缓存名称获取缓存(取得配置文件中预先 定义的sampleCache1设置,通过CacheManager生成一个Cache)
				System.err.println(names.length+"--------"+cache); 
				

				// 获取，更新Cache配置的接口
				 CacheConfiguration configuration = cache.getCacheConfiguration();
				 configuration.setTimeToIdleSeconds(3600);//设置数据钝化时间

				// 缓存在内存中的配置信息，缓存配置动态修改也会体现出来,
				 System.err.println(cm.getActiveConfigurationText());

				// 清除所有缓存的数据，但是缓存本身仍然存在
				 cm.clearAll();

				// 从内存中删除一个缓存以及所有的数据，Cache被销毁
				 cm.removeCache("data-cache");
				 卸载CacheManager ,关闭Cache 
				 cm.shutdown();  */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
