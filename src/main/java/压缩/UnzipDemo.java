package 压缩;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 
 * @author 高鹤
 *
 * 2016年8月14日
 *
 * 作用:解压案例(做的还有问题,只能解压最简单的)
 */
public class UnzipDemo {
	public static void main(String[] args) {
		String sourceFile="F:\\书籍1\\zns源码\\zns源码.zip";//源文件路径
		String outPath="F:\\书籍1";//目標路径
		try {
			zipFile(sourceFile, outPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    /**
     * 解压
     * @param sourceFile:源文件
     * @param outPath:目标文件夹
     * @throws IOException
     */
	public static void zipFile(String sourceFile, String outPath) throws IOException { 
		    FileInputStream fin=new FileInputStream(sourceFile);//文件输入流
		    System.out.println(fin.available());
		  //读取数据和校验数据的输入流流,可用于验证输出数据的完整性.
			CheckedInputStream checkedInputStream=new CheckedInputStream(fin, new CRC32());
			ZipInputStream zipIn=new ZipInputStream(checkedInputStream);//zip格式的输入流
			
			ZipEntry zipEntry=null;//目录条
			while((zipEntry=zipIn.getNextEntry())!=null){//获取目录条
				File targetFile=new File(outPath+File.separator+zipEntry.getName());//压缩条目输出到磁盘上的路径 
			    System.out.println(outPath+File.separator+zipEntry.getName()+"-----"+zipEntry.getName()+"--"+zipIn.getNextEntry());
				if(!targetFile.getParentFile().exists()){//若父目录不存在					
					targetFile.getParentFile().mkdirs();//创建父目录 
				}
				if(targetFile.isDirectory()){
					
					new File(targetFile.getAbsolutePath()+"/").mkdirs();
				}else{
				    if(!targetFile.exists()){//若文件不存在 
				    	targetFile.createNewFile();//创建文件
				    }else{
				    	FileOutputStream outputStream=new FileOutputStream(targetFile);
						byte[] bff=new byte[1024];
						int length=0;
						while((length=zipIn.read(bff))>0){
							outputStream.write(bff, 0, length);//从输出流中写数据到磁盘上
						}
						outputStream.close();
				    }
					
				}
				
			}	
			zipIn.close();
			System.out.println(checkedInputStream.getChecksum().getValue());
			checkedInputStream.close();
			
			fin.close();
		 
	}
}
