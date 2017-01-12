package poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
/**
 * poi生成Excel案例
 * @author 高鹤
 *
 * @date 2016年12月27日
 */
public class PoiCreateExcel {
	public static void main(String[] args) throws Exception {  
	   
	} 
	/**
	 * 无模版生成Excel案例
	 * @throws IOException 
	 */
	static void demo1() throws IOException{
		 // 创建Excel的工作书册 Workbook,对应到一个excel文档  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	  
	    // 创建Excel的工作sheet,对应到一个excel文档的tab  
	    HSSFSheet sheet = wb.createSheet("sheet1");  
	  
	    // 设置excel每列宽度  
	    sheet.setColumnWidth(0, 4000);  
	    sheet.setColumnWidth(1, 3500);  
	  
	    // 创建字体样式  
	    HSSFFont font = wb.createFont();  
	    font.setFontName("Verdana");  
	    font.setBoldweight((short) 100);  
	    font.setFontHeight((short) 300);  
	    font.setColor(HSSFColor.BLUE.index);  
	  
	    // 创建单元格样式  
	    HSSFCellStyle style = wb.createCellStyle();  
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);  
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	  
	    // 设置边框  
	    style.setBottomBorderColor(HSSFColor.RED.index);  
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	  
	    style.setFont(font);// 设置字体  
	  
	    // 创建Excel的sheet的一行  
	    HSSFRow row = sheet.createRow(0);  
	    row.setHeight((short) 500);// 设定行的高度  
	    // 创建一个Excel的单元格  
	    HSSFCell cell = row.createCell(0);  
	  
	    // 合并单元格(startRow，endRow，startColumn，endColumn)  
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));  
	  
	    // 给Excel的单元格设置样式和赋值  
	    cell.setCellStyle(style);  
	    cell.setCellValue("hello world");  
	  
	    // 设置单元格内容格式  
	    HSSFCellStyle style1 = wb.createCellStyle();  
	    style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("h:mm:ss"));  
	  
	    style1.setWrapText(true);// 自动换行  
	  
	    row = sheet.createRow(1);  
	  
	    // 设置单元格的样式格式  
	  
	    cell = row.createCell(0);  
	    cell.setCellStyle(style1);  
	    cell.setCellValue(new Date());  
	  
	    // 创建超链接  
	    HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);  
	    link.setAddress("http://www.baidu.com");  
	    cell = row.createCell(1);  
	    cell.setCellValue("百度");  
	    cell.setHyperlink(link);// 设定单元格的链接  
	  
	    FileOutputStream os = new FileOutputStream("D:\\report\\workbook.xls");  
	    wb.write(os);  
	    os.close();  
	}
	 /**
	  * 有模版生成Excel案例
	 * @throws IOException 
	  */
	  static void demo2() throws IOException{
		  
		  InputStream in = new FileInputStream("D:\\report\\1111.xls");  
          Workbook work = new HSSFWorkbook(in);  
          // 得到excel的第0张表  
          Sheet sheet = work.getSheetAt(0);  
          // 得到第1行的第一个单元格的样式  
          Row rowCellStyle = sheet.getRow(1);  
          CellStyle columnOne = rowCellStyle.getCell(0).getCellStyle();  
          // 这里面的行和列的数法与计算机里的一样，从0开始是第一  
          // 填充title数据  
          Row row = sheet.getRow(0);  
          Cell cell = row.getCell(0);  
          cell.setCellValue("2010年花名测");  
          int i = 2;//计数器  
          int number = 0;  
          // 得到行，并填充数据和表格样式  
          for (;i < 10; i++) {  
              row = sheet.createRow(i);// 得到行  
              cell = row.createCell(0);// 得到第0个单元格  
              cell.setCellValue("琳"+i);// 填充值  
              cell.setCellStyle(columnOne);// 填充样式  
              cell = row.createCell(1);  
              cell.setCellValue("女");  
              cell.setCellStyle(columnOne);// 填充样式  
              cell = row.createCell(2);  
              cell.setCellValue(i+20);  
              cell.setCellStyle(columnOne);// 填充样式  
              // .....给每个单元格填充数据和样式  
              number++;  
          }  
          //创建每个单元格，添加样式，最后合并  
          row = sheet.createRow(i);  
          cell = row.createCell(0);  
          cell.setCellValue("总计：" + number + "个学生");// 填充值  
          cell.setCellStyle(columnOne);  
          cell = row.createCell(1);  
          cell.setCellStyle(columnOne);  
          cell = row.createCell(2);  
          cell.setCellStyle(columnOne);  
          // 合并单元格  
          sheet.addMergedRegion(new CellRangeAddress(i,i,0,2));  
          FileOutputStream os = new FileOutputStream("D:\\report\\workbook.xls");  
          work.write(os);  
          os.close();  
     

  }  
	  
}
