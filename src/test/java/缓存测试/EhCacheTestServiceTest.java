package 缓存测试;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import baseTest.SpringTestCase;
import cache.ehcache.EhCacheTestService;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月24日
 *
 * 作用:测试EhCache与spring结合
 */ 
public class EhCacheTestServiceTest extends SpringTestCase {
	
	@Autowired  
    private EhCacheTestService ehCacheTestService;
	
	@Test  
    public void getTimestampTest() throws InterruptedException{  
        System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("param1"));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("param1"));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + ehCacheTestService.getTimestamp("param1"));
    } 
}
