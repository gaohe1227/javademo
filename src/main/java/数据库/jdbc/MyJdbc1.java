package 数据库.jdbc;

import com.api.Column;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class MyJdbc1
{
  static Map<String, List<Column>> tbs = new ConcurrentHashMap<String, List<Column>> ();
  static ExecutorService es=Executors.newCachedThreadPool(); 
 
  static Properties properties = new Properties();
  static String driverName = null;
  static String url = null;
  static String userName = null;
  static String password = null;
  static String packageLocaltion = null;
  static String basepath;
  static String database = null;
  private static final String CONFIG_FILE = "-configfile";
  private static final String OVERWRITE = "-overwrite";
    static AtomicLong sum=new AtomicLong(0);//统计表的个数

  public static void main(String[] args)
  {
    try
    {
    	System.out.println("开始:-----------------"+new Date());
      Map arguments = parseCommandLine(args);
      String configfile = (String)arguments.get("-configfile");//读取配置文件名称
      File rootFile = new File(System.getProperty("user.dir"));//获取当前目录
      /**
       * 创建配置文件对象
       */
      File configurationFile = new File(rootFile + File.separator + configfile);
      /**
       * 加载文件
       */
      InputStream in = new FileInputStream(configurationFile);
      properties.load(in);
      in.close();

      driverName = properties.getProperty("driverName");
      url = properties.getProperty("url");
      System.out.println("url---"+url);
      userName = properties.getProperty("user");
      password = properties.getProperty("password");
      packageLocaltion = properties.getProperty("package");
      if ((packageLocaltion == null) || (packageLocaltion.trim().equals(""))) {
        throw new Exception("请输入包名");
      }

      database = properties.getProperty("database");
      properties.clear();

      Class.forName(driverName);
      System.out.println("加载驱动完毕");
      /**
       * 创建基本包
       */
      basepath = System.getProperty("user.dir") + File.separator + packageLocaltion;
      File file = new File(basepath);
      if (!(file.exists())) {
        boolean flag = file.mkdir();
        if(flag){
        	   System.out.println("创建目录" + file.getName());
        } 
        Thread.sleep(1000L);
      }
      /**
       * 链接数据库
       */
      Connection connection = DriverManager.getConnection(url, userName, password);
      /**
       * 获取当前数据库所有表
       */
      PreparedStatement statement = connection.prepareStatement("show tables");
      ResultSet resultSet = statement.executeQuery(); 
      while (resultSet.next()) {
        String tableName = resultSet.getString(1); 
        PreparedStatement   statement1 = connection.prepareStatement(
                "select d.column_name, d.column_comment,d.data_type from information_schema.columns d where d.table_name = '" + 
                		tableName + "' and d.TABLE_SCHEMA='" + database + "' ");
        ResultSet  resultSet1 = statement1.executeQuery();
              List columns = new ArrayList();
              while (resultSet1.next()) {
                Column column = new Column();
                column.setComment(resultSet1.getString("column_comment"));//注释
                column.setDataType(resultSet1.getString("data_type"));//数据类型
                column.setName(resultSet1.getString("column_name"));//列名称
                columns.add(column);
              }
              tbs.put(tableName, columns);
              statement1.close();
              //new Thread(new TableColume(tableName,sum),tableName).start();//启东线程
              es.execute(new TableColume(tableName,sum));
      }
      statement.close(); 
      System.out.println("sum:----"+sum); 
    }
    catch (Exception e)
    {
      System.out.println("连接失败");
      e.printStackTrace();
    }
    System.out.println("结束:-----------------"+new Date());
  }

  private static Map<String, String> parseCommandLine(String[] args)
  {
    Map arguments = new HashMap(); 
    for (int i = 0; i < args.length; ++i) {
      System.out.println("-configfile".equalsIgnoreCase(args[i]) + "----" + args[i]);
      if ("-configfile".equalsIgnoreCase(args[i])) {
        if (i + 1 < args.length) {
          arguments.put("-configfile", args[(i + 1)]);
          ++i;
        } else if ("-overwrite".equalsIgnoreCase(args[i])) {
          arguments.put("-overwrite", "Y");
        }
      }
    }

    return arguments;
  }

  static class TableColume
    implements Runnable
  {
    private String tname;//表名称
    private List<Column> columns;//列集合

    public String getTname()
    {
      return this.tname;
    }

    public void setTname(String tname) {
      this.tname = tname;
    }

    public TableColume(String tname,AtomicLong a)
    {
      this.tname = tname;
      this.columns = ((List)MyJdbc1.tbs.get(tname));
      a.incrementAndGet();
    }

    public void run()
    {
    	
      try
      {
        Column column;
        StringBuffer buffer = new StringBuffer();
        StringBuffer headbuffer = new StringBuffer();
        if (MyJdbc1.packageLocaltion != null) {
        	headbuffer.append("package " + MyJdbc1.packageLocaltion + ";\n");
        }
        
        if (this.tname.contains("_")) {
          String[] names = this.tname.split("_");
          this.tname = "";
          for (String s : names)
          {
            TableColume tmp90_89 = this;
            //System.out.println("表名:----"+s);
            tmp90_89.tname = tmp90_89.tname + s.substring(0, 1).toUpperCase() + s.substring(1);//构建表名
          }
        }
        buffer.append("public class " + this.tname.substring(0, 1).toUpperCase() + this.tname.substring(1) + "{\n");//添加类名
        Set<String> set=new HashSet<String>();
        for (Iterator<Column> s = this.columns.iterator(); s.hasNext(); ) {
          column = (Column)s.next();
          String columnName = column.getName();
           ThreadLocal<String> t=new ThreadLocal<String>();
         t.set(columnName);
          if (columnName.contains("_")) {
            String[] names = columnName.split("_");

            columnName = "";
            for ( String name : names)
            {
              if (!(name.trim().equals(""))) 
              {
            	 // System.out.println(t.get()+"-----------"+name+"----------"+tname);
                columnName = columnName + name.substring(0, 1).toUpperCase() + name.substring(1);
              }
            }
          }
          buffer.append("\tprivate " + column.getJavaType() + " " + columnName + ";");
          if(column.getJavaType().equals(null))
         System.out.println(column.getJavaType()+"-------------"+t.get());
          if(column.getJavaType().equalsIgnoreCase("Date")){
        	  set.add("import java.util.Date;\n");
          }
          if ((column.getComment() != null) && (!(column.getComment().trim().equals("")))) {
            buffer.append("//" + column.getComment());
          }
          buffer.append("\n");
        }
        if(set.size()>0){
        	Iterator<String> it = set.iterator(); 
        	while (it.hasNext()) {  
        		  String str = it.next();  
        		  headbuffer.append(str);
        		}  
        }
        for (Iterator<Column> s = this.columns.iterator(); s.hasNext(); ) { column = (Column)s.next();
          buffer.append("\tpublic void set" + column.getName().substring(0, 1).toUpperCase() + 
            column.getName().substring(1) + "(" + column.getJavaType() + " " + column.getName() + 
            " ){\n ");
          buffer.append("\t\tthis." + column.getName() + "=" + column.getName() + ";\n");
          buffer.append("\t}\n");
          buffer.append(
            "\tpublic " + column.getJavaType() + " get" + column.getName().substring(0, 1).toUpperCase() + 
            column.getName().substring(1) + "(){\n ");
          buffer.append("\t\treturn this." + column.getName() + ";\n");
          buffer.append("\t}\n");
        }
        buffer.append("}\n");

        File file = new File(MyJdbc1.basepath + File.separator + this.tname + ".java");

        if (file.exists()) {
          file.delete();
        }

        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        headbuffer.append(buffer);
        writer.write(headbuffer.toString());
        writer.flush();
        writer.close();
        System.err.println("生成" + file.getAbsolutePath() + "结束" +new Date());
      }
      catch (Exception e) {
    	  System.out.println("----"+Thread.currentThread().getName());
        e.printStackTrace();
        System.exit(0);
      }
      
    }
  }
}