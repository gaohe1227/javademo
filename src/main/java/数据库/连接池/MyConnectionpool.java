package ���ݿ�.���ӳ�;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * 
 * @author �ߺ�
 *
 * 2016��6��17��
 *
 * ����:�Զ������ݿ����ӳ�
 */
public class MyConnectionpool {
	private static LinkedList<Connection> pool = new LinkedList<Connection>(); //���ӳ�(���ݿ����ӵļ���)
	static{
		try {
		Properties properties=new Properties();
		InputStream in=MyConnectionpool.class.getClassLoader().getResourceAsStream("jdbc.properties");//�������ļ�������������
	    if(null==in){ 
				throw new FileNotFoundException("�Ҳ���ָ���ļ�"); 
	    }
	    properties.load(in);//�����������ص�Properties��
		 String driver = properties.getProperty("jdbc.driver");  
         Class.forName(driver);//��������  
         String jdbcurl = properties.getProperty("jdbc.url");  
         String nm = properties.getProperty("jdbc.username");  
         String pwd = properties.getProperty("jdbc.password");  
         // ��������ԭ�������ӣ��������Ǵ���  
         String poolSize = properties.getProperty("jdbc.pool.poolSize"); 
         int size = Integer.parseInt(poolSize);  
         for (int i = 0; i < size; i++) {  
             final Connection con = DriverManager.getConnection(jdbcurl, nm,  pwd);  
             // ��con���ж�̬����  
             Object proxyedObj = Proxy.newProxyInstance(MyConnectionpool.class.getClassLoader(),  
                     new Class[] { Connection.class }, new InvocationHandler() {  
                         public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {  
                             // �Ƿ���close����  
                             if (method.getName().equals("close")) {  
                                 synchronized (pool) {  
                                     pool.addLast((Connection) proxy);//�����������ٶ����  
                                     pool.notify();  
                                 }  
                                 return null;// ������õ���close�򲻻���ñ�������ķ�����  
                             }  
                             return method.invoke(con, args);  
                         }  
                     });  
             // ���������ŵ�pool��  
             pool.add((Connection) proxyedObj);  
         }  
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("��ʼ�����ݿ�������Ϣ");
		}
	}
    public static Connection getConn() {  
        synchronized (pool) {  
            if (pool.size() == 0) {  
                try {  
                    pool.wait();  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                return getConn();//�̻߳���֮��ݹ����ȡ����  
            } else {  
                Connection con = pool.removeFirst();//ȡ����һ���ٶȿ�  
                System.err.println("���м�����" + pool.size());  
                return con;  
            }  
        }  
    } 
	public static void main(String[] args) {
		 
	}
}
