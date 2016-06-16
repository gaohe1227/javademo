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
  * @author �ߺ�
  *
  * 2015��12��29��
  *
  * ����:�Զ�����֤�밸��
  */
public class VetifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int WIDTH=70;//��֤��Ŀ��
	private static int HEIGHT=20;//��֤��ĸ߶�

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
		 * ����һ��ͼ�񻺳���
		 */
		BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g=	image.getGraphics();//��ȡ���ʹ���
		g.setColor(new Color(255));//���ñ�����ɫ
		g.fillRect(0, 0, WIDTH, HEIGHT);//���ƻ����ߴ�
		Random random=new Random();
		//��ʼ������֤��
		StringBuilder s=new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int num=random.nextInt(10);//����1��9֮�������
			char c=(char)(random.nextInt(26)+65);//�������A~Z֮�����ĸ
			String str[]=new String[2];
			str[0]=String.valueOf(num);
			str[1]=String.valueOf(c);
			s.append(str[random.nextInt(2)]); 
		}
		g.setColor(new Color(255, 255, 100));//������֤�����ɫ
		g.setFont(new Font(null, Font.ITALIC, 20));//������֤�������
		System.err.println(s.toString());
		g.drawString(s.toString(), 4, 18);//����ָ�����ַ����ĵ�ImageͼƬ��
		for (int i = 0; i < 6; i++) {
			g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
			g.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT),random.nextInt(WIDTH), random.nextInt(HEIGHT));//����
		}
		response.setContentType("image/jpeg");//��ʾ�������һ��ͼƬ
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
