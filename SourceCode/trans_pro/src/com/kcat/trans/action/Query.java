package com.kcat.trans.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.tz.util.TransApi;

/**
 * 类描述：翻译查询类
 * 
 * @author LeeYuBin
 *
 */
@WebServlet(name="query",urlPatterns="/query")  //声明servlet
public class Query extends HttpServlet{
	private static final String APPID="20170718000065115";
	private static final String SECURITY_KEY="yhnIbeIxB81zJx8mj2e0";
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String text=request.getParameter("text");
		String from=request.getParameter("from");
		String to=request.getParameter("to");
		response.getWriter().print(getResult(text, from, to));
	}

	/**
	 * 获取翻译结果的方法
	 * @return
	 */
	public static String getResult(String text,String from,String to) {
		TransApi api=new TransApi(APPID,SECURITY_KEY);   //使用翻译Api
		return api.getTransResult(text, from, to);   //获取参数结果
	}
	
	@Test
	public void test() {   //测试
		System.out.println(getResult("冬天", "zh", "en"));
	}
}
