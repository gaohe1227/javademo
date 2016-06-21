 package 反射.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author 高鹤
 *
 * 2016年6月13日
 *
 * 作用:动态代理安利
 */
public class MyInvocationHandler {
	private Object obj;        //真实主题
	
     public Object bind(Object obj){
	  this.obj=obj;//真实对象
	  return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// TODO Auto-generated method stub
			return method.invoke(obj, args);//实现真实对象的方法
		}
	});//取得代理对象
    }
	  
 
 
}
class MyInvocationHandler1 implements InvocationHandler{
	private Object obj;        //真实主题
	 public Object bind(Object obj){
		  this.obj=obj;
		  return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),  this);//取得代理对象
	    }
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return method.invoke(obj, args);
	}
	
}