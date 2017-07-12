package myvisit;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.NamingException;

import db.DbLib;

public class VisitorDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;
	public VisitorDAO() throws NamingException, SQLException{
		conn=DbLib.getConnection();
		sql="use visitor";
		ps=conn.prepareStatement(sql);
		ps.executeUpdate();
	}
	//保存来访用户
	public int saveVisitor(Visitor v) throws SQLException, NamingException{
		sql="insert into visitors(userid,visittime,lefttime,ip,comefrom) value(?,?,?,?,?)";
		if(conn.isClosed()){			
			conn=DbLib.getConnection();
		}
		ps=conn.prepareStatement(sql);
		ps.setInt(1, v.getUserId());
		ps.setTimestamp(2, new Timestamp(v.getVisitTime().getTime()));
		if(v.getLeftTime()!=null){   //防止空值报错
			ps.setTimestamp(3, new Timestamp(v.getLeftTime().getTime()));			
		}
		else{
			ps.setTimestamp(3, null);
		}
		ps.setString(4, v.getIp());
		ps.setString(5, v.getComeFrom());
		ps.executeUpdate();  //执行完后分配id
		
		sql="select max(id) from visitors";  //拿最大的id
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery(); //执行结果
		rs.next(); //到第一行去
		return rs.getInt(1); //拿第一列
	}
	public ArrayList<Visitor> getVisitors() throws SQLException, NamingException{
		sql="select * from visitors";
		if(conn.isClosed()){			
			conn=DbLib.getConnection();
		}
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList<Visitor> al=new ArrayList<Visitor>();
		while(rs.next()){
			Visitor v=new Visitor();
			v.setId(rs.getInt(1));
			v.setUserId(rs.getInt(2));
			v.setVisitTime(rs.getTimestamp(3));
			v.setLeftTime(rs.getTimestamp(4));
			v.setIp(rs.getString(5));
			v.setComeFrom(rs.getString(6));
			al.add(v);
		}
		return al;
	}
	//修改离开时间和userid
	public void updateVisitor(Visitor v) throws SQLException{
		sql="update visitors set lefttime=?,userId=? where id=?";
		ps=conn.prepareStatement(sql);
		if(v.getLeftTime()!=null){
			ps.setTimestamp(1, new Timestamp(v.getLeftTime().getTime()));			
		}else{
			ps.setTimestamp(1, null);
		}
		ps.setInt(2, v.getUserId());
		ps.setInt(3,v.getId());
		ps.executeUpdate();
	}
}
