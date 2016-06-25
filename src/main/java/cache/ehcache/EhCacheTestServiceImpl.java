package cache.ehcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
 
/**
 * 
 * @author 高鹤
 *
 * 2016年6月24日
 *
 * 作用:Ehcache和spring集成案例
 */
@Service
public class EhCacheTestServiceImpl implements EhCacheTestService {
	/**
	 * 缓存注解
	 */
	@Cacheable(value="cacheTest",key="#param")
	public String getTimestamp(String param) {
		Long timestamp = System.currentTimeMillis();
		System.err.println(timestamp+"----"+param);
		return timestamp.toString();
	}

}
