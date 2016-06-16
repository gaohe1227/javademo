package myannocation;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.Test;

public class AnnocationTest {
	Logger log = Logger.getLogger(AnnocationTest.class);
	String url;
	String username;
	String password;
	String driverName="com.mysql.jdbc.Driver";

	{
		try {
			Class.forName(driverName);
			url = "jdbc:mysql://localhost/myplan";
			username = "root";// 杩欓噷鏇挎崲鎴愪綘鑷凡鐨勬暟鎹簱鐢ㄦ埛鍚�
			password = "root";// 杩欓噷鏇挎崲鎴愪綘鑷凡鐨勬暟鎹簱鐢ㄦ埛瀵嗙爜

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  /**
   * 娉ㄨВ澶勭悊鍣ㄥ簲鐢�
   */
	@Test
	public void testTable()  {
   
		TableAnnocation table = Table.class.getAnnotation(TableAnnocation.class);
		String sql = "select * from " + table.name() + " where 1=1";
		String tableName = table.name();
		log.info(tableName);
		Field[] fields = Table.class.getDeclaredFields();
		Table table2 = new Table();

		if (null != fields && fields.length > 0) {
			for (Field field : fields) {
				if(field.isAnnotationPresent(FieldAnnocation.class)){
					log.info(field.getName() + "-----------" + field.getType().getName());
				}else{
					System.err.println("娌℃湁娉ㄨВ");
				}
				 
			}
		}
		log.info("杩涘叆鏁版嵁搴撴搷浣�-------------------------");
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("杩炴帴鎴愬姛");
			 Statement statement= connection.createStatement();
		     System.out.println( "鍒涘缓Statement鎴愬姛!" );  
		     ResultSet rs=    statement.executeQuery(sql);
		     while(rs.next()){
		    	 Object obj=rs.getString("name");
		    	 System.out.println(obj);
		     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
}
