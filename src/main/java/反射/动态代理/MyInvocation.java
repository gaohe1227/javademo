 package 反射.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 
 * @author 高鹤
 *
 * 作用:事物处理器(动态代理核心类)
 *
 * 2015年11月4日
 */
public class MyInvocation implements InvocationHandler{
    private Object obj;
    
	public MyInvocation(Object obj) {
		super();
		this.obj = obj;
	}
    /**
     * proxy:被代理对象
     * method:被代理对象的方法 
     * args:方法参数
     * obj:目标对象
     */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		Object returnObj=method.invoke(obj, args);
		
		return returnObj;
	}

}