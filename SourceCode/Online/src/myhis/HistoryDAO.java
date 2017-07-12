package myhis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.NamingException;
import db.DbLib;
import myvisit.Visitor;


public class HistoryDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;
	//声明页数,设置setter，getter(count不用set)
	int pageSize;
	int pageNo;
	int pageCount;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageCount() {
		return pageCount;
	}
	
	public HistoryDAO() throws NamingException, SQLException{
		conn=DbLib.getConnection();
		sql="use visitor";   //还是用visitor表
		ps=conn.prepareStatement(sql);
		ps.executeUpdate();
	}
	//保存来访用户
	public void saveHistory(History history) throws SQLException, NamingException{
		sql="insert into history(visitid,visittime,url) value(?,?,?)"; //id不用写，会自动分配
		if(conn.isClosed()){			
			conn=DbLib.getConnection();
		}
		ps=conn.prepareStatement(sql);
		ps.setInt(1, history.getVisitId());
		ps.setTimestamp(2, new Timestamp(history.getVisitTime().getTime()));
		ps.setString(3,history.getUrl());
		
		ps.executeUpdate();  //执行完后分配id
		conn.close();    //关闭资源
	}
	//获取来访用户信息
	public ArrayList<History> getHistory() throws SQLException, NamingException{
		sql="select * from history";
		if(conn.isClosed()){			
			conn=DbLib.getConnection();
		}			
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList<History> al=new ArrayList<History>();
		while(rs.next()){
			History his=new History();
			his.setId(rs.getInt(1));
			his.setVisitId(rs.getInt(2));
			his.setVisitTime(rs.getTimestamp(3));
			his.setUrl(rs.getString(4));
			al.add(his);
		}
		conn.close();
		return al;
	}
	//新建computePageCount
	public void computePgeCount() throws SQLException, NamingException{
		//新建sql
		sql="select count(*) from history";
		if(conn.isClosed()){			
			conn=DbLib.getConnection();
		}
		//包装sql语句
		ps=conn.prepareStatement(sql);
		//执行sql语句返回结果
		ResultSet rs=ps.executeQuery();
		//指向下一行
		rs.next();
		//获取第一个数暂时放pageCount
		pageCount=rs.getInt(1);
		//计算总页数：分类用不用+1
		if(pageCount%2==0){
			pageCount=pageCount/pageSize;
		}else{
			pageCount=pageCount/pageSize+1;
		}
		//关闭连接
		conn.close();
	}
	
	//新建获取本页数据函数
	//与上方sql语句改：select * from history limit"+(pageNo-1)*pageSize+","+pageSize;
	public ArrayList<History> getPageDate() throws SQLException, NamingException{
		//sql="select * from history limit"+(pageNo-1)*pageSize+","+pageSize;  //从第n条开始，截取m条
		sql="select * from history limit 10,10";  //从第n条开始，截取m条!!!!!查询不出来

		if(conn.isClosed()){			
			conn=DbLib.getConnection();
		}			
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList<History> al=new ArrayList<History>();
		while(rs.next()){
			History his=new History();
			his.setId(rs.getInt(1));
			his.setVisitId(rs.getInt(2));
			his.setVisitTime(rs.getTimestamp(3));
			his.setUrl(rs.getString(4));
			al.add(his);
		}
		conn.close();
		return al;
	}
}
