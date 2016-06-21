 package 反射;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author 高鹤
 *
 * 2016年6月14日
 *
 * 作用:反射工具类
 */
public class ReflectUtil {
	/**
	 * 执行方法
	 * 
	 * @param obj:执行方法的对象
	 * @param methodName:方法名称
	 * @param args:方法参数;
	 * @return
	 */
	public Object invoke(Object obj, String methodName, Object... args) {
		Class[] parameterTypes = new Class[args.length];// 获取参数列表的类型集合
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);// 获取方法 
			method.setAccessible(true);
			return method.invoke(obj, args);
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	 /**
	  * 执行方法
	  * @param className:全类名
	  * @param methodName:方法名称
	  * @param args:方法参数
	  * @return
	  */
	public Object invoke(String className, String methodName, Object... args) {
		 
	 try {
		 Object obj= Class.forName(className).newInstance();
		 invoke(obj, methodName, args);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;

	}
 

 

}