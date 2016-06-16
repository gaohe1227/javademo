package 网页解析.jsoupDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlResource {

	/*
	 * public HtmlResource() { // TODO Auto-generated constructor stub }
	 */
	public static String getHtmlResouce(String htmlUrl, String encoding) {
		StringBuffer stringBuffer = new StringBuffer();
		InputStreamReader isr = null;
		URL url = null;
		URLConnection urlConnection = null;
		BufferedReader reader = null;
		try {
			url = new URL(htmlUrl);// 建立网络连接
			urlConnection = url.openConnection();// 开启网络连接
			isr = new InputStreamReader(urlConnection.getInputStream(), encoding);// 建立写入流
			reader = new BufferedReader(isr);// 建立一个缓冲写入流
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line + "\r\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch blockwang
			e.printStackTrace();
			return "网络连接失败或输入网址不正确,请重试";
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
  public static Map<String,String>  getVedioUrlResource(String htmlUrl,String encoding){
	/*	String htmlUrl="http://v.youku.com/v_show/id_XMTAzMjQwMDQ0.html?from=y1.3-idx-uhome-1519-20887.212790-212949.6-2.1-8-1-6-0#paction";
		String encoding="utf-8";*/
		 String htmlStr=getHtmlResouce(htmlUrl,encoding); 
		 Document document=Jsoup.parse(htmlStr);
		 Element element=document.getElementById("panel_share"); 
		 Elements elements=element.getElementsByClass("item");  
		 Map<String,String>  map=new HashMap<>();
		 if(null!=elements){ 
			 System.out.println(elements.size()); 
			 for (Element ele : elements) { 
				 
				Elements es=ele.getElementsByTag("input");
				 if(null!=es){
					for (Element e : es) {
						map.put(e.id(), e.val());
					}
				 }
			}
		 }
		 Set<String> set=map.keySet();
		 Iterator<String> i=set.iterator();
		 while(i.hasNext()){
			 String id=i.next();
			 System.out.println(id+"-----------"+map.get(id));
		 }
		return map;
  }
	public static void main(String[] args) {
		String htmlUrl="http://v.youku.com/v_show/id_XMTAzMjQwMDQ0.html?from=y1.3-idx-uhome-1519-20887.212790-212949.6-2.1-8-1-6-0#paction";
		String encoding="utf-8";
		 Map<String,String> htmlStr=getVedioUrlResource(htmlUrl,encoding);
		// System.out.println(htmlStr);
		 
		/* Document document=Jsoup.parse(htmlStr);
		 Element element=document.getElementById("panel_share"); 
		 Elements elements=element.getElementsByClass("item"); 
 
		 if(null!=elements){
			 System.out.println(elements.size());
			 
			 for (Element ele : elements) {
				 int i=1; 
				Element e=ele.getElementById("link"+i); 
				System.out.println(e==null);
				while(e==null){
					i++;
					e=ele.getElementById("link"+i);  
					if(i==4){
						break;
					}
				}
				if(e!=null){
					
				}
				 
			}
			 for (Element ele : elements) {
				System.out.println(ele.html());
				System.err.println("------------------------------------");
			}
		 }
	*/
	}
}
