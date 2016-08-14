package 缓存.抄的;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/**
 * 
 * @author 高鹤
 *
 * 2016年8月11日
 *
 * 作用:缓存实现类
 */
public class Memoizer<A, V> implements Computable<A, V>{
    private final ConcurrentMap<A, Future<V>> cache=new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;
	public Memoizer(Computable<A, V> c) {
		super();
		this.c = c;
	}
	@Override
	public V compute(final A args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*return null;*/
		while (true) {
		 Future<V> f=cache.get(args);
		 if(f==null){
			 Callable<V> eval=new Callable<V>() {

				@Override
				public V call() throws Exception {
					// TODO Auto-generated method stub
					return c.compute(args);
				}
			};
			FutureTask<V> ft=new FutureTask<>(eval);
			f=cache.putIfAbsent(args, ft);
			if(f==null){
				f=ft;
				ft.run();
			}
			
		 }
		 try {
			return f.get();
		}catch(CancellationException e){
			cache.remove(args, f);
		} 
		 catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}

