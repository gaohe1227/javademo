package ipUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.activation.FileDataSource;

 

public class IpDemo1 {
	public static void main(String[] args) {
		try {
			String ip1 = getMyIP();
			System.out.println("myIP:" + ip1);
			String ip2 = getMyIPLocal();
			System.out.println("myLocalIP:" + ip2);
			InetAddress address = InetAddress.getByName("ready.jiaopeiwang.com");//根据域名获取ip
			System.out.println("ip:---"+address.getHostAddress());
			System.err.println("-------------------------------------------------------------------------------------------------------------------------");
			String utlString=urlString("http://localhost:8080/jiaopei/web/group/index?groupid=1");
		    File file1=new File("demo1.html");
		    if(!file1.exists()){
		    	file1.createNewFile();
		    }
		    OutputStream outputStream=new FileOutputStream(file1);
		    outputStream.write(utlString.getBytes());
		    outputStream.flush();
		    outputStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private static String getMyIP() throws IOException {
		InputStream ins = null;
		try {
			URL url = new URL("http://test.jiaopeiwang.com");
			URLConnection con = url.openConnection();
			ins = con.getInputStream();
			InputStreamReader isReader = new InputStreamReader(ins, "GB2312");
			BufferedReader bReader = new BufferedReader(isReader);
			StringBuffer webContent = new StringBuffer();
			String str = null;
			while ((str = bReader.readLine()) != null) {
				webContent.append(str);
			}
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]");
			return webContent.substring(start, end);
		} finally {
			if (ins != null) {
				ins.close();
			}
		}
	}

	private static String getMyIPLocal() throws IOException {
		InetAddress ia = InetAddress.getLocalHost();//InetAddress.getLocalHost();
		/*InetAddress address = InetAddress.getByName("ready.jiaopeiwang.com");//根据域名获取ip
		System.out.println("ip:---"+address.getHostAddress());*/
		return ia.getHostAddress();
	}
	private static String urlString(String urlString){
		 StringBuffer buffer=new StringBuffer();
		try {
			URL url=new URL(urlString);
		  URLConnection connection=	url.openConnection();
			InputStream inputStream=connection.getInputStream();
			 BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
			 String line="";
			
			 while((line=reader.readLine())!=null){
				// System.out.println(line);
				 buffer.append(line+"\n");
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer.toString();
	}
}