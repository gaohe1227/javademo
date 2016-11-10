package 数据库.连接池.自定义连接池;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * 自定义简易数据库连接池
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月10日
 */
public class DBpool {
	static AtomicInteger curLink = new AtomicInteger(0);// 当前连接数 
	static private int minLink = 0;// 最小连接数 
	static private int maxLink = 0;// 最大连接数
	static private String url=null;
	static private String username=null;
	static private String password=null;

	static LinkedList<MyConnection> connectionList = new LinkedList<MyConnection>();// 储存空闲Connection链接的集合和(涉及多次添加、删除操作、故而用LinkedList)

	static Map<String, String> map = null;
	static {
		map = new HashMap<String, String>();
		Properties p = new Properties();
		try {
			p.loadFromXML(new FileInputStream("DataSourcePool.xml"));// 加载配置文件
			Enumeration<Object> dataSourceSet = p.keys();
			while (dataSourceSet.hasMoreElements()) {
				String key = (String) dataSourceSet.nextElement();
				map.put(key, p.getProperty(key));
			}
			Class.forName(map.get("conectionDriver"));// 加载驱动
			url=map.get("connectionUrl");
			username=map.get("connectionName");
			password=map.get("connectionPassword");
			maxLink = Integer.parseInt(map.get("maxLink"));// 获取最大连接数
			// System.out.println("加载驱动成功");
			minLink = Integer.parseInt(map.get("minLink"));// 获取最小连接数
			for (int i = 0; i < minLink; i++) {
				Connection connection = DriverManager.getConnection(url,username , password);// 创建连接
				connectionList.add(new MyConnection(0, connection));// 添加链接
			}  
			System.err.println("初始化最小链接数minLink=" + minLink + "------------------------------------最大连接数为" + maxLink); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DBpool() {
		// 通过构造函数启动定时器以达到定时释放空闲连接目的
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					// 得到空闲连接，connectionList里面有几个对象就表示有几个空闲连接
					int leisureLink = DBpool.connectionList.size();
					System.out.println("空闲连接数为" + leisureLink + "------------当前连接数为" + curLink.get());
					// 最小连接数
					int minLink = DBpool.minLink;
					// 当空闲连接大于DataSourcePool设置的最小连接数时则关闭
					if (leisureLink > minLink) {
						for (int i = 0; i < leisureLink - minLink; i++) {
							DBpool.closeConnection(DBpool.getConnection());// 关闭空闲连接中多余最小链接的一部分,以求保持空闲连接数与最小连接数相同
							System.out.println("释放空闲链接成功,剩余"+ DBpool.connectionList.size());
						}
					}
					else {
						System.out.println("保持最小连接,将继续保持连接池");
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, Long.parseLong(map.get("connectionTimer")));
	}
    /**
     * 关闭链接
     * @param myConnection
     * @throws SQLException
     */
	protected static void closeConnection(MyConnection myConnection) throws SQLException {
		// TODO Auto-generated method stub
		if (myConnection != null) {
			myConnection.getConnection().close();
			myConnection = null;
		}

	}

	/**
	 * 获取链接
	 * @return
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public static MyConnection getConnection() throws SQLException, InterruptedException {
        
		if (curLink.get() >= maxLink) {// 如果当前链接等于最大连接数
			System.out.println("连接池中无可用链接");
			return null;
		}

		else {

			synchronized (DBpool.class){
				MyConnection conn = null;
				if (connectionList.size() > 0) {
					System.out.println(connectionList.size()+"---"+curLink);  
					conn =   connectionList.getLast();// 从连接池中获取一个链接
					connectionList.remove(conn);
					int i = curLink.incrementAndGet();// 当前连接数增加
					System.out.println(Thread.currentThread().getName() + "获得链接,当前空闲连接数为" + connectionList.size() + ",连接数为=" + i);
				}
				else if (curLink.get() + minLink <= maxLink) {// 当前连接数比最大链接数要小
					for (int i = 0; i < minLink; i++) {
						Connection connection = DriverManager.getConnection(url, username, password);// 创建连接
						connectionList.add(new MyConnection(0, connection));// 添加链接
						System.out.println("添加链接成功");
					}
					conn = connectionList.pop();// 从连接池中获取链接
					curLink.incrementAndGet();// 当前连接数增加
				}
				else if (curLink.get() < maxLink) {
					Connection connection = DriverManager.getConnection(url, username, password);// 创建连接
					conn = new MyConnection(0, connection);
					curLink.incrementAndGet();// 当前连接数增加
				}
				// System.out.println(Thread.currentThread().getName()+"获得链接");
				return conn;
			}

		}

	}

	/**
	 * 释放链接
	 * @return
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public static void releaseConnection(MyConnection myConnection) throws SQLException, InterruptedException {
		Thread.sleep(2000);
		if (myConnection != null) {
			if (connectionList.size() >= maxLink) {
				myConnection.getConnection().close();
				myConnection = null;
				curLink.decrementAndGet();
				return;
			}
			myConnection.setState(0);
			connectionList.add(myConnection);// 将连接回放入连接池
			curLink.decrementAndGet(); 
			System.out.println("释放连接成功" + curLink.get());
		}

	}

	static class MyConnection {
		int state = 0;// 空闲状态

		Connection connection;

		public MyConnection() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MyConnection(int state, Connection connection) {
			super();
			this.state = state;
			this.connection = connection;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public Connection getConnection() {
			return connection;
		}

		public void setConnection(Connection connection) {
			this.connection = connection;
		}
	}
}
