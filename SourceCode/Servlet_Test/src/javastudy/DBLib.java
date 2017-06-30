package javastudy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBLib {
	Connection conn;
	Statement st;
	
	public DBLib() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
	    String url="jdbc:mysql://127.0.0.1:3306";
	  	String user="root";	
	  	String pwd="123456";
	  	conn=DriverManager.getConnection(url,user,pwd);
	  	st=conn.createStatement();  //创建命令
	}
	//创建数据库方法
	public void create_data() throws SQLException{
		String sql="drop database if exists Book;";
		st.executeUpdate(sql);
		sql="create database Book;";
		st.executeUpdate(sql);
	}
	//创建表方法
	public void create_form() throws SQLException{
		String sql="use Book;";
		st.executeUpdate(sql);
		sql="create table BOOKS";
		sql+="(";
		sql+="ID int(4) not null primary key auto_increment,";
		sql+="Name	varchar(100),";
		sql+="Author varchar(50),";
		sql+="Price  decimal,";
		sql+="Publisher varchar(100)";
		sql+=")";
		st.executeUpdate(sql);
		 
	}
	//添加数据方法  
	public void add_data(String path) throws IOException, SQLException{
		FileReader fr=new FileReader(path);
		BufferedReader br=new BufferedReader(fr);
		String line;
		String sql="use Book";
		st.executeUpdate(sql);
		sql="INSERT INTO BOOKS (Name,Author,Price,Publisher) VALUES(?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		
		while((line=br.readLine())!=null){
			String[] arr=line.split(",");
			ps.setString(1, arr[0]);
			ps.setString(2, arr[1]);
			ps.setDouble(3, Double.parseDouble(arr[2]));
			ps.setString(4, arr[3]);
			ps.executeUpdate();
		}
		br.close();
	}
	
}
