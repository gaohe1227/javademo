package 网页解析.jsoupDemo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author 高鹤
 *
 *  2016年3月9日
 *
 *  作用:下载图片
 */
public class DowmImage {
	static Logger log = Logger.getLogger(DowmImage.class);

	public static void main(String[] args) {

		parseHtml("http://www.ipb.cc/pifu/dongman/20140316/5576.html", "gb2312");

	}

	private static void parseHtml(String htmlUrl, String encoding) {
		System.err.println(htmlUrl + "-----------------------");
		if (StringUtil.isBlank(htmlUrl)) {
			return;
		}
		URL url=null;
		try {
			url = new URL(htmlUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String htmlStr = getHtmlResouce(htmlUrl, encoding);
		Document document=null;
		try {
			document = Jsoup.parse(url, 3000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//document.setBaseUri("http://www.3dnew.com");
	 
		Elements eles = document.getElementsByTag("img");
		for (Element element : eles) {
		 
			String src = element.attr("abs:src");
			new Thread(new DownImg(src)).start(); 
 
		}
	}

	private static String getHtmlResouce(String htmlUrl, String encoding) {

		StringBuffer stringBuffer = new StringBuffer();
		InputStreamReader isr = null;
		URL url = null;
		URLConnection urlConnection = null;
		BufferedReader reader = null;
		try {
			url = new URL(htmlUrl);
			urlConnection = url.openConnection();
			urlConnection.setConnectTimeout(500000);
			isr = new InputStreamReader(urlConnection.getInputStream(), encoding);// 建立写入流
			reader = new BufferedReader(isr);// 建立一个缓冲写入流
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				urlConnection = null;
				if (reader != null) {
					reader.close();
				}
				if (isr != null) {
					isr.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stringBuffer.toString();
	}

	static class DownImg implements Runnable {
		private String imgUrl;
		private URL url = null;
		private URLConnection connection = null;
		private OutputStream out = null;
		private File file = null;
		private BufferedInputStream in = null;

		public DownImg(String imgUrl) {
			super();
			this.imgUrl = imgUrl;
			file = new File("F:/图片/" + imgUrl.substring(imgUrl.lastIndexOf("/")));
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				url = new URL(imgUrl);
				connection = url.openConnection();
				//connection.setReadTimeout(5000);
				in = new BufferedInputStream(connection.getInputStream());
				if (in.available() > 0) {
					System.out.println(this.imgUrl + "----------" + in.available());
					out = new FileOutputStream(file);
					int i = 0;
					byte[] b = new byte[1024*5];

					while ((i = in.read(b)) != -1) {
						out.write(b, 0, i);
						// System.out.println(i);
					}
					out.flush();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				connection = null;
				try {
					if (null != in) {
						in.close();
					}
					if (null != out) {

						out.close();

					}
					System.out.println("下载:"+this.imgUrl+"结束");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					/* e.printStackTrace(); */
					// log.info(this.imgUrl+"----"+e.getMessage());
					System.out.println(this.imgUrl);
				}
			}

		}

	}

}
