package ����.��̬����;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 
 * @author �ߺ�
 *
 * ����:���ﴦ����(��̬���������)
 *
 * 2015��11��4��
 */
public class MyInvocation implements InvocationHandler{
    private Object obj;
    
	public MyInvocation(Object obj) {
		super();
		this.obj = obj;
	}
    /**
     * proxy:���������
     * method:���������ķ��� 
     * args:��������
     * obj:Ŀ�����
     */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		Object returnObj=method.invoke(obj, args);
		
		return returnObj;
	}

}
