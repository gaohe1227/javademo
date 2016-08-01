package freemarker;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import com.freemarker.bean.Group;
import com.freemarker.bean.User;

/**
 * Servlet implementation class TsetFreemarkerServelt
 */
@WebServlet("/TsetFreemarkerServelt")
public class TsetFreemarkerServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TsetFreemarkerServelt() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		  FreemarkerUtil util = new FreemarkerUtil();
	        Map<String, Object> map = new HashMap<String, Object>();
	 
	        Group group = new Group();
	        group.setName("IT");
	         
	        User user = new User();
	        user.setId(001);
	        user.setName("����");
	        user.setAge(12);
	        user.setGroup(group);
	         
	        List<User> users = new ArrayList<User>();
	        users.add(user);
	        users.add(user);
	        users.add(user);
	         
	        map.put("user", user);
	        //��ͨEL��ֵ
	        //util.print("01.ftl", map );
	        //�ж�
	        //util.print("03.ftl", map, "03.html");
	        //����
	        //util.print("05.ftl", map);
	        //��Ԫ���ж�
	        util.fprint("06.ftl", map, "06.html",request.getSession().getServletContext().getRealPath("/htmlpackage")); 
	        util.print("06.ftl", map);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
