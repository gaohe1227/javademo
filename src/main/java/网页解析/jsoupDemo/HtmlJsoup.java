package 网页解析.jsoupDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author 高鹤
 *
 *         2016年1月3日
 * 
 *         作用:图片批量采集案例, 通过html解析实现图片的批量下载
 */
public class HtmlJsoup {
	public static void main(String[] args) {
		String htmlReource = HtmlJsoup.getHtmlResourceByUrl("http://tech.qq.com/hlwxw.htm", "gb2312");
		// System.out.println(htmlReource);
		Document document = Jsoup.parse(htmlReource);// 解析网页的源代码
		Elements elements = document.getElementsByTag("img");// 获取网页上的所有图片的地址
		for (Element element : elements) {
			String imgSrc = element.attr("src");
			if (null != imgSrc && !"".equals(imgSrc) && imgSrc.startsWith("http://")) {
				System.out.println("正在下载图片的地址:" + imgSrc);
				downImg("d:\\img1",imgSrc);

			}
		}
		System.err.println("下载完成");
	}

	/**
	 * 获取网页源代码
	 * 
	 * @param url:网页地址
	 * @param encoding:编码
	 * @return
	 * @throws IOException
	 */
	public static String getHtmlResourceByUrl(String htmlUrl, String encoding) {
		StringBuffer buffer = new StringBuffer();
		URL url = null;
		URLConnection connection = null;
		InputStreamReader readerIn = null;
		BufferedReader bufferedReader = null;
		try {
			url = new URL(htmlUrl);// 建立网络连接
			connection = url.openConnection();// 打开网络连接
			readerIn = new InputStreamReader(connection.getInputStream(), encoding);// 创建输入流
			bufferedReader = new BufferedReader(readerIn);// 创建一个缓冲写入文件流
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				buffer.append(line + "\r\n");// 一行一行的追加
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != readerIn)
					readerIn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	/**
	 * 根据图片的路径将图片下载到本地
	 * 
	 * @param filePath:本地文件夹
	 * @param imageUrl:图片网络路径
	 */
	public static void downImg(String filePath, String imageUrl) {
		String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		URL url = null;
		URLConnection connection = null;
		InputStream in=null;
		FileOutputStream fileOutputStream = null;
		try {
			url = new URL(imageUrl);
			connection = url.openConnection();
			in = connection.getInputStream();
			File newFile = new File(filePath + fileName);
			fileOutputStream = new FileOutputStream(newFile);
			 
			byte[] b=new byte[1024];
			int i=0;
			 while((i=in.read(b))!=-1){
				 fileOutputStream.write(b, 0, i);
			 }
			fileOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != in)
					in.close();
				if (null != fileOutputStream)
					fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
