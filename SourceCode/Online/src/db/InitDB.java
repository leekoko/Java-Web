package db;

import java.sql.*;

import javax.naming.NamingException;

import java.io.*;

public class InitDB {
	public static void initialize(String scriptFile) throws NamingException, SQLException, IOException{
		Connection conn=DbLib.getConnection();
		Statement st=conn.createStatement();   //数据库操作
		FileReader fr=new FileReader(scriptFile);
		BufferedReader br=new BufferedReader(fr);
		
		String sql="";
		String line="";
		while((line=br.readLine())!=null){
			if(!line.endsWith(";")){   //非；结尾，继续添加
				sql=sql+line;
				continue;
			}
			sql=sql+line;
			st.addBatch(sql);
			sql="";     //清空sql的内容
		}
		st.executeBatch();   //执行语句
		br.close();   //关闭程序
	}
}
