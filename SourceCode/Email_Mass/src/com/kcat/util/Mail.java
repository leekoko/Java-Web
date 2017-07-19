package com.kcat.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * 邮件群发类
 * @author LeeYuBin
 *
 */
public class Mail extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置字符编码集
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			//接收前端传递的数据
			String name=request.getParameter("m_name");   //收件人
			String topic=request.getParameter("m_topic");   //主题
			String content=request.getParameter("m_content");  //内容
			
//			//账号 用户名 密码
			String username="908230189@qq.com";   //修改为自己的账号密码
			String password="XXXX";
//			
//			//创建工具读取邮箱配置    这里有问题报错：目前还没有解决
			Properties props=new Properties();
			props.put("mail.transport.protocol","smtp");   //邮件传输协议
			props.put("mail.host", "smtp.qq.com");   //邮件主机
			props.put("mail.smtp.auth", true);   //密码安全验证
			


			
//			163邮箱
//			POP3服务器:pop.163.com
//			SMTP服务器:smtp.163.com
//			IMAP服务器:imap.163.com
//			 
//			QQ邮箱
//			POP3：pop.qq.com
//			SMTP：smtp.qq.com
//			SMTP端口号：25
			
			
			Session session=Session.getInstance(props);
			//创建一个邮箱
			MimeMessage ms=new MimeMessage(session);
			Address toAddress;
			
			toAddress=new InternetAddress();
			//邮件的来源
			ms.setFrom(toAddress);
			ms.setRecipients(Message.RecipientType.TO, name);
			ms.setSubject(topic);
			ms.setText(content);
			ms.saveChanges();
			
			//创建放送工具
			Transport ts=session.getTransport();
			ts.connect(username,password);
			ts.sendMessage(ms, ms.getAllRecipients());
			ts.close();
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
