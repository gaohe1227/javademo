package 动态数据源;



import org.aspectj.lang.JoinPoint;

public class DynamicDataSourceInterceptor {

	public void setdataSourcePub(JoinPoint jp) {
		DynamicDataSourceHolder.putDataSource("pub_dataSource");
	}
	
	public void setdataSourcePqk(JoinPoint jp) {
		DynamicDataSourceHolder.putDataSource("pqk_dataSource");
	}
}