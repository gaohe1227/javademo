package jdbc.api;
/**
 * 
 * @author 高鹤
 *
 * 2016年3月31日
 *
 * 作用:表名
 */
public class Table {
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Table(String tableName) {
		super();
		this.tableName = tableName;
	}
	public static void main(String[] args) {
		
		int a=0b000000000000011;
		System.out.println(Integer.toBinaryString(-3)+"---"+"11111111111111111111111111111101".length());
	}
}
