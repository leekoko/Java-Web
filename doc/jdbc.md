# JDBC的使用   

## 1.API的java类  

1. Driver接口：通过操作Driver接口可对驱动程序操作  

   _Driver接口就是大神在开发数据库驱动程序的时候实现了的接口，普通程序员只要找到对应Driver接口进行装载就可以使用这个驱动程序，例如mysql的jdbc驱动程序，其装载代码为`` class.forName("com.mysql.jdbc.Driver")``,引号里面的就是Driver接口_    

2. DriverManager接口：Driver的管理类，用Class.forName注册驱动程序，通过getConnection（三个参数dbUrl，用户名，密码）建立物理链接。  

   _DriverManager叫做Driver的管理类，我们通过这个管理类来使用Driver接口的功能，像getConnection就是用来建立驱动和我们程序“交流桥梁”的方法。_  

- dbUrl的组成：jdbc(协议):mysql(子协议)://10.164.172:3306/cloud_study(子名称)

![](../images/db01.png)

（常见jdbc URL的格式）  

- Connection物理链接作用  

1. 创建Statement对象

Statement是sql容器，可以进行sql语句操作：``ResultSet rs=stmt.sescuteQuery("select userName from user");``   

_我们想象Connection是一座信息交流桥梁，而Statement就像是桥梁上的手扶小拉车，当我们从自身程序通过桥梁走向数据库的时候，拉车上面装的就是一条留言纸（sql语句），到达数据库之后，数据库的工人就会根据留言纸往车上装货物。当然货物是包装成一大件的（ResultSet），里面是整整齐齐的一个个板砖(每个元素)_    

ResultSet对象是sql查询的结果，ResultSet的方法有：下一个，上一个，首个，尾个，指定个。  获取对象的方法：getString(列名),getInt(列名),getObject(列名)   

_通过不同的方法，就可以从大件货物中取出自己所要的那一部分信息_  

## 2.jdbc的构建   

1. 构建的大概步骤  

![](../images/db02.png)  

2. 代码示意  

```java
public class HelloJdbc {
	static final String DB_URL="jdbc:mysql://localhost:3306/test";
	static final String USER="root";
	static final String PASSWORD="123456";
	
	private static void helloword() throws ClassNotFoundException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		//1.装载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		try {
			//2.建立数据连接
			conn=DriverManager.getConnection(DB_URL, USER, PASSWORD);
			//3.执行sql语句
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select userName from user");
			//4.获取执行结构
			while(rs.next()){
				System.out.println("hello"+rs.getString("userName"));
			}
		} catch (SQLException e) {
			//异常处理
			e.printStackTrace();
		}finally {
			//5.清理环境
			try {
				if(conn!=null){
					conn.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
					//ignore
			}
		}
	}
}
```

_首先将所需材料放进来（驱动程序加载），然后使用材料建立数据库和自身程序的桥梁（Connection），这里就需要用到桥梁的路径（url，告诉指向哪里的，什么数据库，什么表），账号和密码。建立好连接桥梁之后，通过小扶手拉车（Statement）运送sql语句到数据库，数据库再返回一个包装好的大件回来（ResultSet），我们通过方法就可以取出想要的砖块（rs.getString("userName")。这一切都执行完之后还没结束，因为桥梁，拉车，大件物品都还散落满地，这将大大占用了空间（系统资源），所以通过.close()方法将其回收起来。至于为什么要判断！=null呢？就是为了防止像桥梁铺设失败，小拉车半路就挂了等这些意外导致这东西本身就不存在。那么强行回收就会使得系统出错，所以要做出判断：你有，并且用完了，那就收起来把~_    







第三个视频开始



