package formate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 聊天信息处理类
 * @author liyb
 *
 */

@WebServlet("/htmlFormat")
public class Formate extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String html=req.getParameter("html");
		
		
		System.out.println("html");*/
	}

	
}
