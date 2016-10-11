package jdbc.批处理;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ����ع�
 * 
 * @author gaohe
 *
 */
public class Test {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// ��������
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ghtest", "root", "1227");

			conn.setAutoCommit(false);
			Statement statement = conn.createStatement();

			statement.executeUpdate("insert into add1 values(1,'14') ");
			statement.executeUpdate("insert into add1 values(2,'14') ");

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

}
