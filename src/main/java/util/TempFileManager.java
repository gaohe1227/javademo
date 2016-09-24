/**
 * 
 */
package util;

import java.io.File;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 删除服务器上的文件
 * 
 * @author xiaoqun.yi
 */
// 实现Runnable接口(推荐)，可以线程接口，预留一个extends(继承)，方便扩展
public class TempFileManager extends Thread {

	// 日志类
	public Log log = LogFactory.getLog(getClass());

	// 临时文件夹目录
	private static String TEMPDIR; //= System.getProperty("java.io.tmpdir");
	private static String NEWTEMPDIR;

	private final static int MINUTE = 1000 * 60;
	private final static int SLEEP_TIME = MINUTE;

	public static boolean isStop = false; // 标示是否停止
	
	public TempFileManager(String path, String newpath){
		this.TEMPDIR = path;
		this.NEWTEMPDIR = newpath;
	}

	
	
	
	// 开始删除
	public void run() {
		try {
			File file = new File(TEMPDIR);
			File newFile = new File(NEWTEMPDIR);
			newFile.mkdirs();

			// 1分钟
			while (!isStop) {
				deleteFolder(file);

				// 休息30分钟
				Thread.sleep(SLEEP_TIME);
			}

		} catch (Exception e) {
			log.error("删除文件失败！" + e);
			e.printStackTrace();
		}

	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	public void deleteFile(File file) {
		try {
			if (file.isFile()) {

				// 只删除在30分钟前创建的文件
				if (canDeleteFile(file)) {
					if (file.delete()) {
						log.info("文件" + file.getName() + "删除成功!");
					} else {
						log.warn("文件" + file.getName() + "删除失败!此文件可能正在被使用");
					}
				}
			}
		} catch (Exception e) {
			log.error("删除文件失败！", e);
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folder
	 */
	public void deleteFolder(File folder) {
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFolder(files[i]);
			}

			// 非当前目录，删除
			if (!folder.getAbsolutePath().equalsIgnoreCase(TEMPDIR)) {

				// 只删除在30分钟前创建的文件
				if (canDeleteFile(folder)) {
					if (folder.delete()) {
						log.info("文件夹" + folder.getName() + "删除成功!");
					} else {
						log.warn("文件夹" + folder.getName()
								+ "删除失败!此文件夹内的文件可能正在被使用");
					}
				}
			}
		} else {
			deleteFile(folder);
		}

	}

	/**
	 * 三十分总前的文件及文件夹才可以删除 文件最后修改时间在30分钟前，返回ture
	 * 
	 * @param file
	 * @return
	 */
	public boolean canDeleteFile(File file) {
		Date date = new Date();
		long lastUpTime = file.lastModified() / 1000;
		long secondsOfOneMinute = 60 * 30;

		// 可删除文件的时间
		long canDelTime = date.getTime() / 1000 - secondsOfOneMinute;

		if (lastUpTime <= canDelTime) {
			return true;
		}
		return false;
	}

	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}

	public static String getTEMPDIR() {
		return TEMPDIR;
	}
	public static void setTEMPDIR(String tEMPDIR) {
		TEMPDIR = tEMPDIR;
	}

	public static boolean isStop() {
		return isStop;
	}
	public static void setStop(boolean isStop) {
		TempFileManager.isStop = isStop;
	}

	public static int getMinute() {
		return MINUTE;
	}
	public static int getSleepTime() {
		return SLEEP_TIME;
	}

}