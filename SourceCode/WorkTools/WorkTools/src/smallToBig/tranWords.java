package smallToBig;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.util.TransApi;

/**
 * 聊天信息处理类
 * @author liyb
 *
 */

@WebServlet("/tranWords")
public class tranWords extends HttpServlet{
	private static final String APPID="20170718000065115";
	private static final String SECURITY_KEY="yhnIbeIxB81zJx8mj2e0";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String text=req.getParameter("text");
		String result=changeWord(text);
		resp.getWriter().print(result);
	}
	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String changeWord(String text){
		
		TransApi api=new TransApi(APPID,SECURITY_KEY);   //使用翻译Api
		return api.getTransResult(text, "zh", "en");   //获取参数结果
	}
	
}
