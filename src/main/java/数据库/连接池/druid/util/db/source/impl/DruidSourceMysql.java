package 数据库.连接池.druid.util.db.source.impl;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import 数据库.连接池.druid.util.config.MySqlConfigProperty;
import 数据库.连接池.druid.util.db.source.AbstractDataSource;
 

public class DruidSourceMysql extends AbstractDataSource {

	private volatile static DruidSourceMysql instance;	
	
	
	private DruidSourceMysql() throws Exception {
		dataSource = DruidDataSourceFactory.createDataSource(
				MySqlConfigProperty.getInstance().getProperties());		
	}

	public static DruidSourceMysql getInstance() throws Exception {
		if (instance == null) {
			synchronized (DruidSourceMysql.class) {
				if (instance == null) {
					instance = new DruidSourceMysql();
				}
			}
		}
		return instance;
	}	
	
}
