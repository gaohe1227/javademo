package ����.��̬����;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author �ߺ�
 *
 * 2016��6��13��
 *
 * ����:��̬������
 */
public class MyInvocationHandler {
	private Object obj;        //��ʵ����
	
     public Object bind(Object obj){
	  this.obj=obj;//��ʵ����
	  return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// TODO Auto-generated method stub
			return method.invoke(obj, args);//ʵ����ʵ����ķ���
		}
	});//ȡ�ô������
    }
	  
 
 
}
class MyInvocationHandler1 implements InvocationHandler{
	private Object obj;        //��ʵ����
	 public Object bind(Object obj){
		  this.obj=obj;
		  return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),  this);//ȡ�ô������
	    }
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return method.invoke(obj, args);
	}
	
}