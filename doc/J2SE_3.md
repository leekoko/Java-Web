# 3.J2SE：J2SE轻松入门第三季  

## 1.泛型  
定义方式：``class MyTest<T>{     T var;  ...``  
泛型传的只能是引用类型  
调用方式：``MyTest<String> mt=new MyTest<String>();``  

## 2.MySql  
java连接数据库的方法：JDBC  
安装之后让MySql随系统启动启动（在 服务 中可以设置，也可以设置手动：net start mysql/ net stop mysql）  
### 1.创建数据库  
1. 数据库名词  
view 视图  
stored procedure 存储过程：用sql语法编写的程序  
结构化语言：用来增删改查  

**创建student数据库，新建studinfo表，字段为no，name，sex，age**

2. 常用命令  
增：insert into studinfo values('002','李四'，'男'，20)  
删：update studinfo set name='李五' where no = '002'    
改：delete studinfo where no='002'  
查：select * from studinfo  
select name,chinese from studinfo,grade where studinfo.no==grade.no(联表查询)  

### 3.JDBC访问数据库  
1. 首先下载JDBC的驱动程序  
2. 引入到项目中（项目下：内部jar包  项目外：外部jar包）  
3. 编写程序：加载驱动程序到内存``Class.forName("com.mysql.jdbc.Driver");``   
4. 创建连接：``Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/student","root","123456");``(需要账号密码，使用sql下的jar包而不是mysql)  
5. 创建Statement：``Statement st=conn.createStatement();``  
6. 执行sql语句：``st.executeQuery("select * from studinfo");``返回的是ResultSet  
7. 循环打印,从1开始打印,用``while(rs.next())``循环，每层循环打印n个，每个用rs.getString(i)获取  

**加载jdbc，创建连接后读取student数据库studinfo表（4个字段）的所有数据**  
```java
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "123456");
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery("select * from studinfo");
	while(rs.next()){
		for (int i = 1; i <= 4; i++) {
			System.out.print(rs.getString(i)+"\t");
		}
		System.out.println();
	}
}
```  

8. 获取字段标题:``ResultSetMetaData data=rs.getMetaData(); data.getColumnName(i)``    

### 4.增加数据  
1. 增加，修改，数据删除都没有返回数据，所以用st.executeUpdate("sql语句");  
2. 使用占位符的方式添加数据更加高效：先``conn.prepareStatement(sql);``,再``ps.setString(1,"003")``,最后``ps.executeUpdate();``执行    








