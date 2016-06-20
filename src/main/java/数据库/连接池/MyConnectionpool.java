package 数据库.连接池;

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
 * @author 高鹤
 *
 * 2016年6月17日
 *
 * 作用:自定义数据库连接池
 */
public class MyConnectionpool {
	private static LinkedList<Connection> pool = new LinkedList<Connection>(); //连接池(数据库链接的集合)
	static{
		try {
		Properties properties=new Properties();
		InputStream in=MyConnectionpool.class.getClassLoader().getResourceAsStream("jdbc.properties");//将配置文件放入输入流中
	    if(null==in){ 
				throw new FileNotFoundException("找不到指定文件"); 
	    }
	    properties.load(in);//将输入流加载到Properties中
		 String driver = properties.getProperty("jdbc.driver");  
         Class.forName(driver);//加载驱动  
         String jdbcurl = properties.getProperty("jdbc.url");  
         String nm = properties.getProperty("jdbc.username");  
         String pwd = properties.getProperty("jdbc.password");  
         // 创建三个原生的连接，都将它们代理  
         String poolSize = properties.getProperty("jdbc.pool.poolSize"); 
         int size = Integer.parseInt(poolSize);  
         for (int i = 0; i < size; i++) {  
             final Connection con = DriverManager.getConnection(jdbcurl, nm,  pwd);  
             // 对con进行动态代理  
             Object proxyedObj = Proxy.newProxyInstance(MyConnectionpool.class.getClassLoader(),  
                     new Class[] { Connection.class }, new InvocationHandler() {  
                         public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {  
                             // 是否是close方法  
                             if (method.getName().equals("close")) {  
                                 synchronized (pool) {  
                                     pool.addLast((Connection) proxy);//链接往最后放速度最快  
                                     pool.notify();  
                                 }  
                                 return null;// 如果调用的是close则不会调用被代理类的方法。  
                             }  
                             return method.invoke(con, args);  
                         }  
                     });  
             // 将代理对象放到pool中  
             pool.add((Connection) proxyedObj);  
         }  
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("初始化数据库配置信息");
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
                return getConn();//线程唤醒之后递归继续取链接  
            } else {  
                Connection con = pool.removeFirst();//取出第一个速度快  
                System.err.println("还有几个：" + pool.size());  
                return con;  
            }  
        }  
    } 
	public static void main(String[] args) {
		 
	}
}
