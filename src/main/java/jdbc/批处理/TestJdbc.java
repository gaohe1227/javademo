package jdbc.批处理;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestJdbc {
public static void main(String[] args) {
	Connection conn=null;
	try{
		Class.forName("com.mysql.jdbc.Driver");//��������
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1227");
		Statement st = conn.createStatement();//����ִ�о��
		ResultSet rs = st.executeQuery("select * from navigate");//���ؽ����
		while(rs.next()){
			System.out.println("id"+rs.getInt(1)+"   text:"+rs.getString(2)+"  leaf:"+rs.getInt(3)+"  url:"+rs.getString("url"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			conn.close();//�ر���Դ
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
