package insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * 
 * @author 高鹤
 *
 * 2016年8月24日
 *
 * 作用：jdbc工具类
 */
public class JdbcTool {
	PreparedStatement statement = null;

	Connection connection;
	final String URL = "jdbc:mysql://localhost:3306/cms";
	final String USERNAME = "root";
	final String PASSWORD = "root";
	static {
		// 加载MySql的驱动类
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException { // 连接数据库
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return connection;
	}

	private PreparedStatement getPreparedStatement(String sql)
			throws SQLException {// 预执行sql
		statement = connection.prepareStatement(sql);
		return statement;
	}

	private void close() throws SQLException {// 关闭链接
		if (connection != null) {
			connection.close();
		}
		if (statement != null) {
			statement.close();
		}
	}

	public void insertAll(List<User> users) {
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			connection
					.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO user(id,name,level,password,createTime) VALUES ");

			for (int i = 0; i < 1000; i++) {

				sql.append("(" + users.get(i).getId() + ",'"
						+ users.get(i).getName() + "',"
						+ users.get(i).getLevel() + ",'"
						+ users.get(i).getPassword() + "','"
						+ users.get(i).getDateString() + "'),");
			}
			String preparedSqlString = sql.substring(0, sql.length() - 1);
			System.out.println(preparedSqlString);
			statement = connection.prepareStatement(preparedSqlString);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		JdbcTool jbJdbcTool = new JdbcTool();
		List<User> users = new ArrayList<User>();
		Random ra = new Random();
		Long start = System.currentTimeMillis();
		for (int j = 0; j < 10000000; j++) {

			User user = new User(j + 1, ChineseName.getChinese(),
					ra.nextInt(1000), "123456", new Date());
			users.add(user);
			if ((j + 1) % 1000 == 0) {
				System.err.println(j + 1);
				jbJdbcTool.insertAll(users);
				users.clear();
			}

		}
		Long end = System.currentTimeMillis();
		System.err.println("花费:" + (end - start));

	}

}

class User {
	private int id;
	private String name;
	private int level;
	private String password;
	private Date createTime;
	private String dateString;

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public User(int id, String name, int level, String password, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.password = password;
		this.createTime = createTime;
		this.dateString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.format(createTime);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}