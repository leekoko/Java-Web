package db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbLib {
	public static Connection getConnection() throws NamingException, SQLException{
		   InitialContext initCtx = new InitialContext();
		   DataSource ds = (DataSource)initCtx.lookup("java:comp/env/mytest");
		   return ds.getConnection();
	}
}
