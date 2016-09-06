package 动态数据源;



import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSouce();
	}

}

