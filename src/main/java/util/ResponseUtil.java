package util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;


public class ResponseUtil {
	public static void write(HttpServletResponse response,Object o) throws Exception{
		//使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
		//例如web浏览器就是通过MIME类型来判断文件是GIF图片。通过MIME类型来处理json字符串。

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(o.toString());
		out.flush();
		out.close();
	}

}
