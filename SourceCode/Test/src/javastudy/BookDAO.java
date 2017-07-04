package javastudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO {
	Connection conn;
	Statement st;
	String sql;
	PreparedStatement ps;
	
	public BookDAO() throws ClassNotFoundException,SQLException{
		System.out.println("进入初始化");
		Class.forName("com.mysql.jdbc.Driver");
	    String url="jdbc:mysql://127.0.0.1:3306/book";
	  	String user="root";	
	  	String pwd="123456";
	  	conn=DriverManager.getConnection(url,user,pwd);
	  	st=conn.createStatement();  //创建命令
	}
	//增
	public int addBook(Book book) throws SQLException{
		System.out.println("添加图书");
		sql="insert into books (Name,Author,Price,Publisher) values(?,?,?,?) ";
		ps=conn.prepareStatement(sql);
		ps.setString(1, book.getName());
		ps.setString(2, book.getAuthor());
		ps.setFloat(3, book.getPrice());
		ps.setString(4, book.getAuthor());
		return ps.executeUpdate();
	}
	//删
	public int deleteBook(int id) throws SQLException{
		sql="delete from books where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		return ps.executeUpdate();
	}
	//改
	public int modifyBook(int id,Book book) throws SQLException{
		sql="update books set name=?,author=?,price=?,publisher=? where id =?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, book.Name);
		ps.setString(2, book.getAuthor());
		ps.setFloat(3, book.getPrice());
		ps.setString(4, book.getPublisher());
		ps.setInt(5, id);
		return ps.executeUpdate();
	}
	//查
	public ArrayList<Book> getBooks() throws SQLException{
		System.out.println("进入了列表");
		ArrayList<Book> books=new ArrayList<Book>();
		sql="select * from books";
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()){
			Book book=new Book();
			book.setId(rs.getInt(1));
			book.setName(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setPrice(rs.getFloat(4));
			book.setPublisher(rs.getString(5));
			books.add(book);
		}
		return books;
	}
	public ArrayList<Book> getBooks(String bookName) throws SQLException{
		ArrayList<Book> books=new ArrayList<Book>();   //用数组是因为同名可能有多个
		sql="select * from books where name = ?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, bookName);
		ResultSet rs=ps.executeQuery(sql);
		while(rs.next()){
			Book book=new Book();
			book.setId(rs.getInt(1));
			book.setName(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setPrice(rs.getFloat(4));
			book.setPublisher(rs.getString(5));
			books.add(book);
		}
		return books;
		
	}
	public Book getBookById(int id) throws SQLException{
		String sql="select * from books where id =?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		rs.next();
		Book book=new Book();
		book.setId(rs.getInt(1));
		book.setName(rs.getString(2));
		book.setAuthor(rs.getString(3));
		book.setPrice(rs.getFloat(4));
		book.setPublisher(rs.getString(5));
		return book;
	}
}
