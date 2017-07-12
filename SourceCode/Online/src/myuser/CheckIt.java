package myuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import myvisit.*;

/**
 * Servlet implementation class CheckIt
 */
@WebServlet("/CheckIt")
public class CheckIt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		User user=new User();
		user.setUserName(request.getParameter("uname"));   //uname来自于点击中表格传过来的
		user.setPwd(request.getParameter("pwd")); 
		int id=0;
		PrintWriter out=response.getWriter();
		try {
			UserDAO dao =new UserDAO();
			id=dao.getUserId(user); //获取id值
			if(id==0){  //如果id值为0，表示不存在
				response.sendRedirect("login.jsp");  //跳转登录页面
				return;   //自己加上
				//我要传一个提示过去，强哥说用session！！！！
				//？？？？
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//到这里的就是id存在，验证通过的情况
			//修改用户在线表，将id写入

		Visitor v=(Visitor) session.getAttribute("USER");  //拿到当前访客
		v.setUserId(id);   //新的独有的userid,有个毛用？？？，是注册的时候给的吗？？？
		try {
			VisitorDAO dao=new VisitorDAO();
			dao.updateVisitor(v);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("main.jsp");  //跳转主页面
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
