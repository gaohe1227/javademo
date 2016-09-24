package 缓存.map;

import java.util.Date;

public class Demo1 {
public static void main(String[] args) {
	CacheMap cacheMap=CacheMap.getDefault();
	cacheMap.put("1", new Date());
}
}
