package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import jdbc.api.Column;
import jdbc.api.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

/**
 * 
 * @author 高鹤
 *
 * 2016年3月31日
 *
 * 作用:自用的实体类生成器
 */
public class MyJdbc {
	static Map<String, List<Column>> tbs = new HashMap<>();;
	static Properties properties = new Properties();// 读取配置文件
	static String driverName = null;
	static String url = null;
	static String userName = null;
	static String password = null;
	static String packageLocaltion = null;
	static String basepath;
	static String database = null; 
    private static final String CONFIG_FILE = "-configfile"; //$NON-NLS-1$
    private static final String OVERWRITE = "-overwrite"; //$NON-NLS-1$
    public static void main(String[] args) {
		try {
		     
	        Map<String, String> arguments = parseCommandLine(args); 
	        String configfile = arguments.get(CONFIG_FILE);
	        File rootFile=new File(System.getProperty("user.dir"));
	  
	        File  configurationFile= new File(rootFile+File.separator+configfile);//获取配置文件   
			InputStream in =new FileInputStream(configurationFile); 
			properties.load(in);
			in.close();

			driverName = properties.getProperty("driverName");
			url = properties.getProperty("url");
			userName = properties.getProperty("user");
			password = properties.getProperty("password");
			packageLocaltion = properties.getProperty("package");
			if (null == packageLocaltion || packageLocaltion.trim().equals("")) {
				throw new Exception("请输入包名");
			}

			database = properties.getProperty("database");
			properties.clear();

			Class.forName(driverName);// 加载驱动
			System.out.println("加载驱动完毕");
			basepath = System.getProperty("user.dir") + File.separator + packageLocaltion;
			File file = new File(basepath);
			if (!file.exists()) {
				boolean flag = file.mkdir(); 
				System.out.println("创建目录"+file.getName());
				Thread.sleep(1000);
			}
			Connection connection = DriverManager.getConnection(url, userName, password);// 连接数据库
			PreparedStatement statement = connection.prepareStatement("show tables");
			ResultSet resultSet = statement.executeQuery();
			List<Table> tables = new ArrayList<>();
			while (resultSet.next()) {
				String tableName = resultSet.getString(1);
				tables.add(new Table(tableName));
			}
			statement.close();
			connection.close();
			if (tables.size() > 0) {
				connection = DriverManager.getConnection(url, userName, password);
				for (int i = 0; i < tables.size(); i++) {
					statement = connection.prepareStatement(
							"select d.column_name, d.column_comment,d.data_type from information_schema.columns d where d.table_name = '"
									+ tables.get(i).getTableName() + "' and d.TABLE_SCHEMA='" + database + "' ");
					resultSet = statement.executeQuery();
					List<Column> columns = new ArrayList<>();
					while (resultSet.next()) {
						Column column = new Column();

						column.setComment(resultSet.getString("column_comment"));
						column.setDataType(resultSet.getString("data_type"));
						column.setName(resultSet.getString("column_name"));
						columns.add(column);
					}
					tbs.put(tables.get(i).getTableName(), columns);
				}

			}
			statement.close();
			connection.close();
			if (tbs.size() > 0) {
				Set<String> keySet = tbs.keySet();
				Iterator<String> i = keySet.iterator();
				while (i.hasNext()) {
					String tname = i.next();
					try {
						new Thread(new TableColume(tname)).start();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

		} catch (Exception e) {
			System.out.println("连接失败");
			e.printStackTrace();
		}
	}

	static class TableColume implements Runnable {
		private String tname;
		private List<Column> columns;

		public String getTname() {
			return tname;
		}

		public void setTname(String tname) {
			this.tname = tname;
		}

		public TableColume(String tname) {
			super();
			this.tname = tname;
			this.columns = tbs.get(tname);

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				StringBuffer buffer = new StringBuffer();
				if (packageLocaltion != null) {
					buffer.append("package " + packageLocaltion + ";\n");
				}

				if (tname.contains("_")) {
					String[] names = tname.split("_");
					tname = "";
					for (String s : names) {
						tname += s.substring(0, 1).toUpperCase() + s.substring(1);
					}
				}
				buffer.append("public class " + tname.substring(0, 1).toUpperCase() + tname.substring(1) + "{\n");
				for (Column column : columns) {
					String columnName = column.getName();
					if (columnName.contains("_")) {
						String[] names = columnName.split("_");

						columnName = "";
						for (String s : names) {

							if (!s.trim().equals("")) {
								columnName += s.substring(0, 1).toUpperCase() + s.substring(1);
							}
						}
					}
					buffer.append("\tprivate " + column.getJavaType() + " " + columnName + ";");
					if (column.getComment() != null && !column.getComment().trim().equals("")) {
						buffer.append("//" + column.getComment() + "");
					}
					buffer.append("\n");
				}
				for (Column column : columns) {
					buffer.append("\tpublic void set" + column.getName().substring(0, 1).toUpperCase()
							+ column.getName().substring(1) + "(" + column.getJavaType() + " " + column.getName()
							+ " ){\n ");
					buffer.append("\t\tthis." + column.getName() + "=" + column.getName() + ";\n");
					buffer.append("\t}\n");
					buffer.append(
							"\tpublic " + column.getJavaType() + " get" + column.getName().substring(0, 1).toUpperCase()
									+ column.getName().substring(1) + "(){\n ");
					buffer.append("\t\treturn this." + column.getName() + ";\n");
					buffer.append("\t}\n");
				}
				buffer.append("}\n");

				File file = new File(basepath + File.separator + tname + ".java");

				if (file.exists()) {
					file.delete();
				}

				file.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(buffer.toString());
				writer.flush();
				writer.close();
				System.err.println("生成" + file.getAbsolutePath() + "结束");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static Map<String, String> parseCommandLine(String[] args) { 
        Map<String, String> arguments = new HashMap<String, String>();

        for (int i = 0; i < args.length; i++) {
        	System.out.println(CONFIG_FILE.equalsIgnoreCase(args[i])+"----"+args[i]);
            if (CONFIG_FILE.equalsIgnoreCase(args[i])) {
                if ((i + 1) < args.length) {
                    arguments.put(CONFIG_FILE, args[i + 1]); 
                i++;
            } else if (OVERWRITE.equalsIgnoreCase(args[i])) {
                arguments.put(OVERWRITE, "Y"); //$NON-NLS-1$
            } 
        } 
       
        }
        return arguments;
	}
}
