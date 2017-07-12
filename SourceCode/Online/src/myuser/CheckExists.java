package myuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myvisit.Visitor;

/**
 * Servlet implementation class CheckExists
 */
@WebServlet("/CheckExists")
public class CheckExists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckExists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("username");
		int id=0;
		try {
			UserDAO dao=new UserDAO();
			id=dao.getIdByName(name);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//若id为0，说明没有这个用户
		//设置编码，判断id为0，输出用户名不存在，return结束运行
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		if(id==0){   //说明已登录
			out.println("用户名不存在");
			return;
		}	
		
//判断在线用户表中是否有该id		
		ServletContext application=request.getServletContext(); //application的获取
		@SuppressWarnings("unchecked")
		HashMap<String,Visitor> map=(HashMap<String,Visitor>)application.getAttribute("ONLINE");
		Set<String> ids=map.keySet();
		Iterator<String> it=ids.iterator();
		Visitor current=null;
		while(it.hasNext()){
			String sid=it.next();
			Visitor v=map.get(sid);
			if(v.getUserId()==id){
				current=v;
				break;
			}
		}
		
		if(current!=null){   //说明已登录
			out.println("当前用户已于IP为["+current.getIp()+"]的设备登录");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
