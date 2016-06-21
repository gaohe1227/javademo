 package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 /**
  * 
  * @author 高鹤
  *
  * 2015年12月29日
  *
  * 作用:自定义验证码案例
  */
public class VetifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int WIDTH=70;//验证码的宽度
	private static int HEIGHT=20;//验证码的高度

    /**
     * Default constructor. 
     */
    public VetifyCode() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	response.getWriter().append("Served at: ").append(request.getContextPath());*/
		/**\
		 * 建立一个图像缓冲区
		 */
		BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g=	image.getGraphics();//获取画笔工具
		g.setColor(new Color(255));//设置背景颜色
		g.fillRect(0, 0, WIDTH, HEIGHT);//绘制画布尺寸
		Random random=new Random();
		//开始绘制验证码
		StringBuilder s=new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int num=random.nextInt(10);//生成1到9之间的整数
			char c=(char)(random.nextInt(26)+65);//随机生成A~Z之间的字母
			String str[]=new String[2];
			str[0]=String.valueOf(num);
			str[1]=String.valueOf(c);
			s.append(str[random.nextInt(2)]); 
		}
		g.setColor(new Color(255, 255, 100));//设置验证码的颜色
		g.setFont(new Font(null, Font.ITALIC, 20));//设置验证码的字体
		System.err.println(s.toString());
		g.drawString(s.toString(), 4, 18);//绘制指定的字符串的到Image图片上
		for (int i = 0; i < 6; i++) {
			g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
			g.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT),random.nextInt(WIDTH), random.nextInt(HEIGHT));//画线
		}
		response.setContentType("image/jpeg");//表示输出的是一张图片
		OutputStream out=response.getOutputStream();
		ImageIO.write(image, "jpeg", out);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}