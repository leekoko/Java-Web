package common;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import myhis.History;
import myhis.HistoryDAO;
import myvisit.*;
import java.util.*;

@WebListener
public class MyListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {
	HttpServletRequest request;
    /**
     * Default constructor. 
     */
    public MyListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	HttpSession session=arg0.getSession();
    	session.setMaxInactiveInterval(10*60);

    	Visitor v=new Visitor();
    	v.setIp(request.getRemoteAddr());
    	v.setComeFrom(request.getHeader("Referer"));  //获取来源的网站
    	v.setVisitTime(new Date());
    	//保存到数据库中
    	VisitorDAO dao;
    	int id=0;
    	try {
			dao=new VisitorDAO();
			id=dao.saveVisitor(v);  //存入数据库     拿到id,日后可用
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	v.setId(id);  //拿到id后，写入visitor中
    	//保存到在线用户列表中,保存session id，访问信息
       	ServletContext application=arg0.getSession().getServletContext();
    	@SuppressWarnings("unchecked")
		HashMap<String, Visitor> map=(HashMap<String, Visitor>) application.getAttribute("ONLINE");
    	map.put(session.getId(), v);
    	//application.setAttribute("ONLINE", map);	//改了之后map中的内容自动改，不用再加app存入
    	
    	session.setAttribute("USER", v);  //存过id，直接放USER给下面使用
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0)  { 
    	request=(HttpServletRequest) arg0.getServletRequest();
    	History his=new History();
    	his.setVisitTime(new Date());
    	his.setUrl(request.getRequestURI().toString());   //获取浏览的url地址
    	HttpSession session=request.getSession();   //获得session
    	Visitor v=(Visitor)session.getAttribute("USER");//拿上面存的Visitor（有id）
    	his.setId(v.getId());  //把上面的id取出来存his中，用户和用户访问都是一个id
    	try {
			HistoryDAO dao=new HistoryDAO();
			dao.saveHistory(his);   //把带上信息的his存起来
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         //将用户从在线用户表中删除
    	ServletContext app=arg0.getSession().getServletContext();
    	@SuppressWarnings("unchecked")
		HashMap<String, Visitor> map=(HashMap<String, Visitor>)app.getAttribute("ONLINE");
    	String id=arg0.getSession().getId();

//多余的对比寻找出id
//    	Set<String> ids=map.keySet();  //从app中取出id
//    	Iterator<String> it =ids.iterator();
//    	while(it.hasNext()){
//    		id=it.next();
//    		if(id.equals(arg0.getSession().getId())){  //从app中取得的id与当前id比较
//    			break;
//    		}
//    	}
		//删除之前，先改当前用户在数据库的离开时间
		Visitor v=map.get(id);   //拿出用户
    	v.setLeftTime(new Date());
    	try {
    		VisitorDAO dao=new VisitorDAO();
			dao.saveVisitor(v);
		} catch (SQLException |NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    	//将它在在线用户表删除
    	map.remove(id);
//    	app.setAttribute("ONLINE", map);    可以省略此步骤
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	//服务器刚启动的时候，保存一个空的hashmap到application中
    	HashMap<String, Visitor> map=new HashMap<String,Visitor>();
       	ServletContext application=arg0.getServletContext();
    	application.setAttribute("ONLINE", map);
    }
}
