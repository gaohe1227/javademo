package jdbc.api;

/**
 * 
 * @author 高鹤
 *
 * 2016年3月31日
 *
 * 作用:数据库表中的列
 */
public class Column {
	private String name;// 列名
	private String dataType;// 字段类型
	private String javaType;// java类型
	private String comment;//注释

	public Column() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Column(String name, String dataType, String javaType) {
		super();
		this.name = name;
		this.dataType = dataType;
		this.javaType = javaType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
	 
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
		if (dataType.trim().equalsIgnoreCase("VARCHAR")||dataType.trim().equalsIgnoreCase("CHAR")) {
			this.javaType = "String";
		} else if (dataType.trim().equalsIgnoreCase("int")||dataType.trim().equalsIgnoreCase("tinyint")||dataType.trim().equalsIgnoreCase("smallint")||dataType.trim().equalsIgnoreCase("integer")) {
			this.javaType = "Integer";
		} else if (dataType.trim().equalsIgnoreCase("text")) {
			this.javaType = "String";
		} else if (dataType.trim().equalsIgnoreCase("long")||dataType.trim().equalsIgnoreCase("bigint")) {
			this.javaType = "Long";
		} 
		 else if("double".equalsIgnoreCase(dataType.trim())||"float".equalsIgnoreCase(dataType.trim())){
			 this.javaType = "Double";
		}else if("clob".equalsIgnoreCase(dataType.trim())){
			this.javaType = "CLob"; 
		}else if("blob".equalsIgnoreCase(dataType.trim())){
			this.javaType = "BLob"; 
		}else if("date".equalsIgnoreCase(dataType.trim())){
			this.javaType = "Date"; 
		}else if("time".equalsIgnoreCase(dataType.trim())){
			this.javaType = "Time"; 
		}else if("timestamp".equalsIgnoreCase(dataType.trim())){
			this.javaType = "Timestamp"; 
		}
		
		
		
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
}
