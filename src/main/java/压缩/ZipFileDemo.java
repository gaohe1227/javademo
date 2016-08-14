package 压缩;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author 高鹤
 *
 * 2016年8月14日
 *
 * 作用:压缩案例
 */
public class ZipFileDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sourcePath="F:\\zns源码";//源文件路径
		String outPath="E:\\新建文件夹\\2.zip";//压缩文件路径
		try {
			zip(sourcePath, outPath);//压缩
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 压缩文件或文件夹
	 * @param sourcePath:源文件或文件夹路径
	 * @param outPath:输出压缩文件路径
	 * @throws IOException 
	 */
	public static void zip(String sourcePath, String outPath) throws IOException {
		 
			File sourceFile=new File(sourcePath);//源文件
			FileOutputStream fout=new FileOutputStream(outPath);//输出流
			//写入数据和校验数据的输出流,可用于验证输出数据的完整性.
			CheckedOutputStream checkedOutputStream=new CheckedOutputStream(fout,new CRC32());
			ZipOutputStream zipout=new ZipOutputStream(checkedOutputStream);//zip格式的输出流
			String zipFileName=sourceFile.getName();//压缩文件名称
			zipDirectory(sourceFile, zipout, zipFileName);  
			zipout.close();//关闭压缩流 
			System.out.println(checkedOutputStream.getChecksum().getValue());
			checkedOutputStream.close();
			fout.close();//关闭文件输出流
	 
	}
	 /**
	  * 压缩目录
	  * @param sourceFile:源文件
	  * @param zipout：压缩输出流
	  * @param zipFileName:压缩文件名
	  * @throws IOException
	  * @throws FileNotFoundException
	  */
	public static void zipDirectory(File sourceFile, ZipOutputStream zipout,String zipFileName) throws IOException, FileNotFoundException {
		if(sourceFile.isDirectory()){//目录
			 for (File file : sourceFile.listFiles()) {
				if(file.isFile()){//如果是文件
					zipFile(file,zipout,zipFileName+File.separator+file.getName());//压缩文件
				}else{//如果是目录
					if(file.listFiles().length>0){
						zipDirectory(file, zipout,zipFileName+File.separator+file.getName());//递归压缩文件夹
					}else{
					 zipout.putNextEntry(new ZipEntry(zipFileName+File.separator+file.getName()+"/"));	//将压缩条目写入到压缩目录当中去;"/"为告知程序此文件夹为空文件夹
					 zipout.closeEntry();//关闭当前压缩条目
					}
				}
			}
		}else{
			zipFile(sourceFile,zipout,zipFileName);
		}
	}
   /**
    * 压缩文件
    * @param sourceFile:源文件
    * @param zipout:压缩流
    * @param zipFileName:压缩条目的文件名称
    * @throws IOException
    * @throws FileNotFoundException
    */
	public static void zipFile(File sourceFile, ZipOutputStream zipout, String zipFileName)
			throws IOException, FileNotFoundException {
		zipout.putNextEntry(new ZipEntry(zipFileName));//将一个要压缩的文件写入到压缩条目中
		FileInputStream fin=new FileInputStream(sourceFile);//创建文件输入流
		byte[] bff=new byte[1024];
		int length=0;
		while((length=fin.read(bff))>0){//将源文件写入到zip格式输出流
			zipout.write(bff, 0, length);
		}
		zipout.closeEntry();//关闭条目
		fin.close();
		//关闭文件输入流
		 
	}

}
