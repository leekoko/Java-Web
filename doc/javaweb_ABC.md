# 6.javaweb快速入门 

## 1.jsp基础使用    

### 1.前端设计  
因为这一部分主要学的是jsp，所以前端代码直接提供,不予解释  
```html
<form name="f1" action="show.jsp">
	<table>
		<tr><td>用户名</td><td><input type="text" name="uname"></td></tr>
		<tr><td>密码</td><td><input type="password" name="pwd"></td></tr>
		<tr><td>重复密码</td><td><input type="password" name="pwd1"></td></tr>
		<tr>
			<td>所在省份</td>
			<td>
				<select name="prov">
					<option value="北京">北京</option>
					<option value="上海">上海</option>
					<option value="天津">天津</option>
					<option value="重庆">重庆</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>兴趣爱好</td>
			<td>
				<input type="checkbox" name="hobby" value="爬山">爬山
				<input type="checkbox" name="hobby" value="上网">上网
				<input type="checkbox" name="hobby" value="看书">看书
				<input type="checkbox" name="hobby" value="下棋">下棋
				<input type="checkbox" name="hobby" value="游泳">游泳
				<input type="checkbox" name="hobby" value="乒乓球">乒乓球
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="提交信息"></td>
		</tr>
	</table>
	
</form>	
```
1. submit按钮要验证后才给予提交，在submit中添加onclick属性``onclick="return check()"``,在js中写return false还是true来确定是否提交    

### 2.jsp页面接受  
1. 在另一个jsp页面中用request接收  
  ``String uname=request.getParameter("uname");``  
  当获取的是多个值得时候（多选框），用数组存储：``String[] arr=request.getParameter("hobby");``  
2. 在另一个页面线显示用``out.print("你的用户名是"+uname);``  
3. 运行测试，每次修改都得重新启动服务器  
> jsp表格提交    

show.jsp
```html
<%
	String uname=request.getParameter("uname");
	String pwd=request.getParameter("pwd");
	String prov=request.getParameter("prov");
	String[] hobby=request.getParameterValues("hobby");
	out.print("姓名："+uname+"\t");
	out.print("密码："+pwd+"\t");
	out.print("城市："+prov+"\t");
	out.print("爱好：");
	for(int i=0;i<hobby.length;i++){
		out.print(hobby[i]+" ");
	}
%>
```



### 3.用js验证数据  

1. 去除空格：``str=str.replace(/\s|/g,"");``  
2. 光标定位，focus()方法  
3. 用innnerHTML插入文字提示  
4. 检查非空，遍历多选数组，如果没内容则输出提示，return false  
5. 如果是普通按钮，没有提交功能，需要添加``document.f1.submit();``进行提交  
```javascript
function checkit(){
	var str=document.getElementsByName("uname").item(0).value;
	str=str.replace(/\s|/g,"");
	if(str.length<=0){
		document.getElementById("info").innerHTML="请输入名字";
		return false;
	}
	return true;
}
```

### 4.用jq验证数据  
1. $.trim()可以将内容进行去空格化  
2. 使用submit按钮，当jq返回的是fasle的时候，内容不会提交  
3. .each()方法可以对数组对象进行遍历，参数e表示当前对象  
4. 如果不是submit按钮，需要在jq中添加``$("form[name='f1']").submit()``进行事件提交  
```javascript
	$(function(){
		$("form[name='f1']").submit(function(){
			if($.trim($(":text[name='uname']").val()).length==0){
				$(":text[name='uname']").select();
				$(":text[name='uname']").focus();
				$("#info").html("用户名不为空！");
				return false;
			}
			if($.trim($(":password[name='pwd']").val()).length==0){
				$(":password[name='pwd']").select();
				$(":password[name='pwd']").focus();
				$("#info").html("密码不为空！");
				return false;
			}
			var pwd=$(":password[name='pwd']").val();
			var pwd1=$(":password[name='pwd1']").val();
			if(pwd!=pwd1){
				$("#info").html("两次输入的密码不一致！");
				return false;
			}
			//至少选择一种爱好
			var times=0;
			$(":checkbox[name='hobby']").each(function(i,e){
				if(e.checked){
					times++;
				}
			});
			if(times==0){
				$("#info").html("请至少选择一个爱好");
				return false;
			}
			return true;
		});
	});
```

[案例源码](../SourceCode/javaWeb_ABC/)   

---

## 2.jsp结构构成  
jsp的运行原理：写的jsp文件会被翻译成servlet文件  

### 1.jsp脚本元素  
1. <%编写的是方法内的内容%>...<%隔开的这些内容和上面那部分是同个方法中的，参数可以共享%>  
2. <%!编写的是和方法同级的方法%> 
3. <%=getData()%>可以直接访问jsp的代码  

### 2.jsp指令元素  
1. page指令：page指令可以有多个，用来定义jsp的全局属性  
2. include指令：可以将文件包含到jsp文件中``<%@include file="filename"%>``  
3. taglib指令：引入标签库，可以是自定义标签也可以内部标签  

### 3.Jsp动作元素  

JSP动作元素用来控制JSP行为，执行一些常用的JSP页面动作。  

1. 创建对象<jsp:useBean>：  

   ``<jsp:useBean id="u" class="javastudy.UserInfo"></jsp:useBean>``是一个动作元素，它的作用是创建一个对象(名字为u，类为UserInfo)  

2. 属性赋值<jsp:setProperty>：将提交表格中name的属性设置到创建的对象中，``<jsp:setProperty name="u" property="*">``(提交表格中的name必须和对象属性一致，而该标签的name需要与创建对象标签的id一致)  

   当只要获取一部分的属性，那就不用*，而用具体的属性名``<jsp:setProperty name="u" property="userName">``    

3. 获取属性<jsp:getProperty>：``<jsp:getProperty name="u" property="userName"></jsp:getProperty>``,要指定显示哪一个对象的哪一个属性    

4. 包含网页<jsp:include>：``<jsp:include>``,它和include指令的区别就是：

   1. include指令：在运行前会将引用内容包含到java文件中（静态包含）  
   2. include动作：在运行java文件的时候再翻译引用的jsp文件，把结果包含进来（引入执行页面或应答文本）  

5. 传递参数<jsp:param>：``<jsp:param value="北京甲骨文科技" name="firm" />``，  

   接收方式：``<%=request.getParameter("firm")%>``(可以通过include动作把参数传过去)  

6. 跳转页面<jsp:forward>：里面还可以传参数过去：  

   ```jsp
   <jsp:forward page="hello.jsp">
   	<jsp:param value="<%=request.getParameter(\"uname\")>" name="a" />
   </jsp:forward>
   ```

   (这里只能用相对地址跳转，双引号里面用转义)  

7.  嵌入插件<jsp:plugin>： 用来在JSP中嵌入Java插件，如Applet。  


### 4.案例  

#### 1.传递数据  

> 新建UserInfo类（userName,vip,password），在另一个页面来接收表单传过来的属性值，赋值后显示出来  

1. 编写一个UserInfo的javaBean  
2. 编写表格，用来输入属性值，用post提交到show.jsp（注意name属性跟javaBean属性一致）  
3. 在show.jsp中：
   1. 创建UserInfo对象  
   2. 为对象设置属性  

- 前端设计  

```html
<form action="show.jsp" method="post">
	<table>
		<tr>
			<td>用户名</td>
			<td><input type="text" name="userName" /></td>
		</tr>
		<tr>
			<td>是否VIP</td>
			<td><input type="checkbox" name="vip" /></td>
		</tr>		
		<tr>
			<td>密码</td>
			<td><input type="text" name="password" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="保存信息"/></td>
		</tr>
	</table>
</form>
```

show.jsp  

```jsp
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="u" class="javastudy.UserInfo"></jsp:useBean>
<!-- 设置值 -->
<jsp:setProperty property="*" name="u"/>

<jsp:getProperty property="userName" name="u"/><br>
<jsp:getProperty property="vip" name="u"/><br>
<jsp:getProperty property="password" name="u"/><br>
```

#### 2.传参数  

> 将表单内容提交到本页面，判断之后将数据传到下一个页面  

1. 除了可以提交到show.jsp页面，也可以不写提交给自身页面。需要判断是提交过来还是第一次打开，判断有没有传userName过来  
2. 内嵌双引导需要转义  
3. 可以在跳转后的页面获取到<jsp:param>传过来的值  

- 前端设计  

```html
<form method="post" action="">
	<table>
		<tr><td>姓名</td><td><input type="text" name="name"></td></tr>
		<tr><td colspan="2"><input type="submit" value="保存数据"></td></tr>
	</table>
</form>
```

Test.jsp

```jsp
<%
request.setCharacterEncoding("utf-8");
if(request.getParameter("name")!=null){
	
%>
	<jsp:forward page="show.jsp">
		<jsp:param value="<%=request.getParameter(\"name\") %>" name="a"/>
	</jsp:forward>
<%
}
%>
```

show.jsp

```jsp
你好，<%=request.getParameter("name") %>
```

---

## 3.JSP内置对象      

内置对象由服务器（Tomcat）创建，可以直接使用    

request、response、out、session、application、pageContext、page、config、exception   

### 1.out对象        

out对象属于JspWriter类，可以向客户端输出信息：``out.print("hello");``    

### 2.request对象        

request对象可以获取客户端传递到服务器的信息，它是HttpServletrequest的实例，只有在同一次请求可见    

常用方法：

1. ``request.getParameter("uname")``获取一个参数的值  
2. ``request.getParameterNames()``获取传给服务器所有参数的名字，返回的是Enumeration类型  


Enumeration的使用方式：判断下一个是否有元素e.hasMoreElements()，获取下一个的元素e.nextElements()  

3. ``request.getParameterValues("uname")``获取一个参数的多个值，像checkbox  
4. ``request.setAttribute("name","微软科技有限公司");``设置属性  
5. ``request.getAttribute("name")``获取这个属性值  
6. ``request.removeAttribute("name");``去掉这个属性  
7. ``request.getAttributeNames()``获取多个属性名字  
8. ``request.getCookies()``把个人信息存放在客户端  
9. ``request.getCharacterEncoding()``获取设置的编码  
10. ``request.getContentLength()``获取整个网页的长度  
11. ``request.getMethod()``获取get（直接输入地址）/post方法  
12. ``request.getRemoteAddr()``获取远程主机地址  
13. ``request.getRemoteHost()``获取远程主机名称  
14. ``request.getServerName()``获取服务器名称  
15. ``request.getServerPort（）``获取服务端口  
16. ``request.getRequestURL()``获取访问地址，URI就是URL-http://localhost:8080  
17. ``request.getServletPath()``jsp转化为Servlet的路径  
18. ``request.getContextPath()``子目录的名字  
19. ``request.getHeaderNames()``获取头文件信息，获取头文件``request.getHeader(name)``,信息有语言，用户浏览器，操作系统，cpu，服务器等    

### 3.response对象    

responset对象可以向客户端发出请求，它是HttpServletresponse的实例  

1. ``response.sendRedirect("url地址");``跳转到指定网页  
2. ``response.getWriter().print("你好");``往网页打东西，跟out.print一样效果  
3. 各种Header的操作  

### 4.session对象  

session对象是一个会话可见，tomcat会话时间默认是30min，其父接口为HttpSession   

1. ``session.setAttribute("COLOR", color);``设置属性  
2. ``session.getAttribute("COLOR");``获取属性值  


(session在页面上关闭的方式：在page标签中添加``session="false"``)  

> 输入颜色，保存颜色到session，然后另一个页面再从session获取颜色  

1. 按钮绑定事件``$("#c").bind("click",function(){...})``，判断如果文本框输入内容长度为0，则选择文本框，return结束运行    
2. 否则设置背景色为文本框输入颜色，并且调用ajax把文字内容传到save.jsp页面中（设置鼠标点击打开窗口事件）  
3. save.jsp页面用request获取到传来的数据，将其存进session中  
4. page1.jsp页面再css样式中设置背景色，参数为session的内容  


- 前端页面  

```html
<input type="button" value="设置颜色" id="c"/><input type="text" id="color"><br>
<input type="button" value="打开窗口1" id="p1"/>
```

main.jsp

```javascript
<script type="text/javascript">
	$(function(){
		$("#c").click(function(){
			if($.trim($("#color").val()).length==0){
				$("#color").focus();
				return;
			}
			$("body").css("background-color",$("#color").val());
			$.ajax("save.jsp?color="+$("#color").val());
		});
		$("#p1").bind("click",function(){
			window.open("show.jsp");
		});
	});
</script>
```

save.jsp

```java
<%
String color=request.getParameter("color");
session.setAttribute("COLOR", color);
%>
```

show.jsp

```css
<style type="text/css">
body{
background-color: <%=session.getAttribute("COLOR")%>
}
</style>
```

[案例源码](../SourceCode/Session_T/)   

### 5.appliction对象  

application设置的对象整个网站共用，所有会话可见，并且不会过期（服务器运行可见），其实现的接口是ServleContext    

1. ``application.getRealPath("");``获取真实的路径（最后部署完成的目录）  

### 6.page对象    

page对象就是页面转化为Servlet类的实例，所以当我运行page.getClass().getName()获取到的就是当前网页编译后的Servlet的文件名（Object page=this）    

### 7.config对象    

config用来配置指定的jsp参数，像在web.xml中配置初始化参数，那么通过config在网页上得到它 （也可以得到servletName），其属于ServletConfig的接口  

 ### 8.exception对象    

在page标签中添加errorPage=“error.jsp”,在页面出现错误的时候，就会自动跳转到错误页面（error.jsp中也需要指明这是一个错误页面：page标签中添加isErrorPage="true"）,跳转之后可以用exception对象来获取错误信息``<%out.print(exception.getMessage());%>``  

### 9.pageContext对象     

1. pageContext可以获取前面八大对象，例如``pageContext.getOut().print()``(applicetion&config比较特殊，使用的是getServletContext()&getServletConfig()),其所属的类是PageContext类      
2. 可以设置范围  
   1. ``pageContext.setAttribute("name","john");``值本页面有效  
   2. ``pageContext.setAttribute("name","john",pageContext.REQUEST_SCOPE);``同一个request有效，相当于``request.setAttribute("name","john");``  
   3. ``pageContext.setAttribute("name","john",pageContext.SESSION_SCOPE);``同一个session有效，相当于``session.setAttribute("name","john");``  
   4. ``pageContext.setAttribute("name","john",pageContext.APPLICATION_SCOPE);``同一个application有效，相当于``application.setAttribute("name","john");``    

  ---

## 4.Servlet  

### 1.用jsp页面实现初始化    

1. 创建DBLib类  

   编写创建数据库方法，创建表方法，添加数据方法  

2. 声明一个连接``Connection conn``,编写构造函数中进行初始化：

```java
public DBLib() throws ClassNotFoundException,SQLException{
  	Class.forName("com.mysql.jdbc.Driver");
    String url="jdbc:mysql://127.0.0.1:3306";
  	String user="root";	
  	String pwd="123456";
  	conn=DriverManager.getConnection(url,user,pwd);
  	st=conn.createStatement();  //创建命令
}
```

3. 编写创建数据库方法  

用``st.executeUpdate(sql);``执行sql语句，sql语句为先删除原数据库``drop database if exists Book;``，再创建新数据库``create database Book;``   

4. 编写创建表方法  

将sql语句执行  

``use Book;``  

```sql
create table BOOKS
 (
   ID int(4) not null primary key auto_increment,
   Name	varchar(100),
   Author varchar(50),
   Price  decimal,
   Publisher varchar(100)
 )
```

(长的语句+=组起来再执行)  

5. 编写添加数据方法  
   1. 执行sql语句``use Book``  
   2. 读取文件：新建FileReader对象，对传进来的filename路径进行读取，为了加速读取，用到BufferedReader包装类  
   3. 取出一行的方式：br.readLine(),判断这一行是否为空，非空的话对该行数据进行切分（存入数组中），关闭读取对象  
   4. 新建PreparedStatement对象，传入sql语句``INSERT INTO BOOKS (Name,Author,Price,Publisher) VALUES(?,?,?,?)``  
   5. 然后对sql语句设置参数，设置的方式：``ps.setString(1,values[0]);``(除了String，还有double等)  
6. 使用jsp执行数据初始化类    
   1.  编写按钮初始化，用jq给按钮绑定一个页面，这个页面专门调用初始化类，初始化成功执行回调函数  
   2.  jsp初始化方式：先new一个对象，执行其初始化方法（添加数据方法需要传路径，用application.getRealPath("")获取真实路径，加上文件夹和文件名构成完整路径）

### 2.用Servlet初始化    

Servlet是用来处理逻辑的，而jsp是用来显示结果。所以讲上面的jsp页面改为Servlet页面。Servlet主要有doGet&doPost方法  

Model 1:jsp+JavaBeans  

Model 2:jsp+JavaBeans+Servlet  

1. 创建Servlet，新建初始化对象，调用其方法(但是application是在jsp中的九大内置对象，在Servlet中没有，所以要通过``this.getServletContext()``来获取application对象)  

2. 调用url（定义url有/，调用的时候没有），在jsp页面直接调用注解上的url即可  

   上面使用的是注解配置的方式，除此之外还有web.xml的配置方式：  

   1. 配置类和所属的名字：为类设置一个名字  

   ```xml
   <servlet>
     <servlet-name>a</servlet-name>
     <servlet-class>javastudy.InitIt</servlet-class>
   </servlet>  
   ```

   2. 配置访问路径：将名字和访问路径连接起来  

   ```xml
   <servlet-mapping>
   	<servlet-name>a</servlet-name>
     	<url-pattern>/myservlet</url-pattern>
   </servlet-mapping>
   ```

3. out返回执行完的信息：在jsp中，输出信息是out输出，但是Servlet中没有out对象，所以用``PrintWriter out=response.getWriter()``来获取out对象  


（在servlet中设置编码格式用response，因为是传出）  


> 调用jsp页面初始化数据库，调用servlet初始化数据库    

InitIt.jsp  

```javascript
$(function(){
	$(".btn").click(function(){
		$.post("init_data",function(data){   //注解方式的url
			$("#info").html(data);
		});
	});
});
```

DBLib.java  

```java
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
```

init.jsp  

```java
<%
DBLib lib=new DBLib();
lib.create_data();
lib.create_form();
lib.add_data(application.getRealPath("")+"/data/data.txt");
out.print("初始化成功");
%>
```

init_data.java  

```java
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			DBLib lib=new DBLib();
			lib.create_data();
			lib.create_form();		
			lib.add_data(this.getServletContext().getRealPath("")+"/data/data.txt");
			response.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			out.print("初始化成功2");
		} catch (SQLException|ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
```

[案例源码](../SourceCode/Servlet_Test/)   

---

## 5.Servlet图书管理案例  

> 做一个表格，有增删改查的功能  

1. 执行sql代码,新建数据表  

```sql
 create table BOOKS
 (
   ID int(4) not null primary key auto_increment,
   Name	varchar(100),
   Author varchar(50),
   Price  decimal,
   Publisher varchar(100)
 )
```

2. 建立JavaBean，其属性有id,Name,Author,Price,Publisher  
3. 建立DAO类，构造函数连接数据库，基础增删改查方法  

```java
public class BookDAO {
	Connection conn;
	Statement st;
	String sql;
	PreparedStatement ps;
	
	public BookDAO() throws ClassNotFoundException,SQLException{
		System.out.println("进入初始化");
		Class.forName("com.mysql.jdbc.Driver");    //加载驱动
	    String url="jdbc:mysql://127.0.0.1:3306/book";   
	  	String user="root";	
	  	String pwd="123456";
	  	conn=DriverManager.getConnection(url,user,pwd);   //连接参数
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
```

执行sql语句，当有返回值得时候，用executeQuery()，当没有返回值的时候用executeUpdate()  

当没有返回值得时候返回运行后的情况(为int型)  

4. 用bookList.jsp页面遍历所有书本信息（用隐藏标签藏id，记得设置name）  

bookList.java

```css
$(function(){
	$("input[type='checkbox'][name='selectit']:first").click(function(){
		$("input[type='checkbox'][name='selectit']").not(":first").prop("checked",$(this).prop("checked"));
	});
	$("input[type='button'][name='modifyit']").click(function(){
		//获取id
		var id=$(this).parent().parent().children().eq(0).children().eq(0).val();
		//页面跳转
		location.href="modify.jsp?id="+id;
	});
	$("#btnDelete").click(function(){
		var ids="";
		var num=0;
		$("input[type='checkbox'][name='selectit']").each(function(i,e){
			if(i>0){
				if($(this).prop("checked")){
					var id=$(this).parent().parent().children().eq(0).children().eq(0).val();
					ids+=id;
					num++;
					if(!$(this).is(":last")){
						ids+=",";
					}
				}
			}
		});
		var answer =confirm("你确定要删除这"+num+"本图书吗？");
		if(!answer){
			return;
		}
		location.href="DeleteIt?ids="+ids;
	});
	$("#btnAdd").click(function(){
		location.href="add.jsp";
	});
});
```

```html
<table>
	<tr><th>序号</th><th>图书名称</th><th>作者</th><th>价格</th><th>出版社</th><th>修改</th><th><input type="checkbox" name="selectit"></th></tr>
<%
	BookDAO dao=new BookDAO();
	ArrayList<Book> al=dao.getBooks();
	for(int i=0;i<al.size();i++){
		Book book=al.get(i);		
%>
	<tr>
		<td><%=i+1%><input type="hidden" name="id" value="<%=book.getId()%>"></td>
		<td><%=book.getName() %></td>
		<td><%=book.getAuthor() %></td>
		<td><%=book.getPrice() %></td>
		<td><%=book.getPublisher() %></td>
		<td><input type="button" name="modifyit" value="修改"></td>
		<td><input type="checkbox" name="selectit"></td>
	</tr>
	
<%
	}
%>	
	<tr>
		<td><input type="button" id="btnDelete" value="Delete"/></td>
		<td><input type="button" id="btnAdd" value="Add"/></td>		
	</tr>
</table>
```

5. 点击删除的话，将id传给servlet，servlet收到之后将其分割成数组，删除对应的id信息  

DeleteIt.java  

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");
		String[] id=ids.split(",");
		try {
			BookDAO dao=new BookDAO();
			for (int i = 0; i < id.length; i++) {
				dao.deleteBook(Integer.parseInt(id[i]));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("bookList.jsp");
	}
```

6. 点击修改，增加的话，新开一个页面（修改需要根据id将信息预设进去），然后提交到servlet中，最后将对象传入DAO中（修改还需要id）  

modify.jsp

```html
<%
	int id=Integer.parseInt(request.getParameter("id"));
	BookDAO dao=new BookDAO();
	Book book=dao.getBookById(id);
%>

<form method="post" action="Modify">
	<table>
		<input type="hidden" name="id" value="<%=id%>" />
		<tr><td>序号</td><td><input type="text" name="name" value="<%=book.getName()%>"></td></tr>
		<tr><td>作者</td><td><input type="text" name="author" value="<%=book.getAuthor()%>"></td></tr>
		<tr><td>价格</td><td><input type="text" name="price" value="<%=book.getPrice()%>"></td></tr>
		<tr><td>出版社</td><td><input type="text" name="publisher" value="<%=book.getPublisher()%>"></td></tr>
		<tr><td colspan="2"><input type="submit" value="修改图书信息"></td><td><div id="info"></div></td></tr>
	</table>
</form>

```

Modify.java  

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String author=request.getParameter("author");
		float price=Float.parseFloat(request.getParameter("price"));
		String publisher=request.getParameter("publisher");
		Book book=new Book();
		book.Name=name;
		book.Author=author;
		book.Price=price;
		book.Publisher=publisher;
		BookDAO dao;
		try {
			dao=new BookDAO();
			dao.modifyBook(id, book);
			response.sendRedirect("bookList.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

add.jsp  

```html
<form method="post" action="AddIt">
	<table>
		<tr><td>序号</td><td><input type="text" name="name"></td></tr>
		<tr><td>作者</td><td><input type="text" name="author" "></td></tr>
		<tr><td>价格</td><td><input type="text" name="price" ></td></tr>
		<tr><td>出版社</td><td><input type="text" name="publisher"></td></tr>
		<tr><td colspan="2"><input type="submit" value="添加图书"></td><td><div id="info"></div></td></tr>
	</table>
</form>
```

AddIt.java  

```java
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String author=request.getParameter("author");
		float price=Float.parseFloat(request.getParameter("price"));
		String publisher=request.getParameter("publisher");
		Book book=new Book();
		book.Name=name;
		book.Author=author;
		book.Price=price;
		book.Publisher=publisher;
		BookDAO dao;
		try {
			dao=new BookDAO();
			dao.addBook(book);
			response.sendRedirect("bookList.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

[案例源码](../SourceCode/Servlet_BookSys/)   

**进入下一章：[7.Java Web 进阶开发](javaweb_Advanced.md)**