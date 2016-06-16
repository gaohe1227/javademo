package ����;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author �ߺ�
 *
 * 2016��6��14��
 *
 * ����:���乤����
 */
public class ReflectUtil {
	/**
	 * ִ�з���
	 * 
	 * @param obj:ִ�з����Ķ���
	 * @param methodName:��������
	 * @param args:��������;
	 * @return
	 */
	public Object invoke(Object obj, String methodName, Object... args) {
		Class[] parameterTypes = new Class[args.length];// ��ȡ�����б�����ͼ���
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);// ��ȡ���� 
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
	  * ִ�з���
	  * @param className:ȫ����
	  * @param methodName:��������
	  * @param args:��������
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
