package myuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import db.DbLib;

public class UserDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;
	public UserDAO() throws NamingException, SQLException{
		conn=DbLib.getConnection();
		sql="use visitor";   //还是用visitor表
		ps=conn.prepareStatement(sql);
		ps.executeUpdate();
	}
	
	public int getUserId(User user) throws SQLException, NamingException{
		if(conn.isClosed()){
			conn=DbLib.getConnection();
		}
		sql="select id from users where username=? and pwd=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, user.userName); //传入用户名
		ps.setString(2, user.pwd); //传入用户密码
		ResultSet rs=ps.executeQuery(); //获取执行结果
		int id=0;
		if(rs.next()){ //判断有无内容
			id=rs.getInt(1);   //取第一行
		}
		conn.close(); 
		return id;   //如果大于0返回true
	} 
	//根据id获取名字
	public String getNameById(int id) throws SQLException, NamingException{
		if(conn.isClosed()){			
			conn=DbLib.getConnection();
		}
		sql="select username from users where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		String name=null;
		if(rs.next()){
			name=rs.getString(1);  //获取第一个		
		}
		conn.close();
		return name;
	}
	//通过名字拿id
	public int getIdByName(String name) throws SQLException, NamingException{
		if(conn.isClosed()){			
			conn=DbLib.getConnection();
		}
		sql="select id from users where username=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		int id=0;
		if(rs.next()){
			id=rs.getInt(1);  //获取第一个		
		}
		conn.close();
		return id;
	}
	
}
