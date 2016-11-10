package com.api;
/**
 * 
 * 表信息
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月7日
 */
public class Table
{
  private String tableName;

  public String getTableName()
  {
    return this.tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public Table(String tableName)
  {
    this.tableName = tableName;
  }

  public static void main(String[] args) {
    int a = 3;
    System.out.println(Integer.toBinaryString(-3) + "---" + "11111111111111111111111111111101".length());
  }
}