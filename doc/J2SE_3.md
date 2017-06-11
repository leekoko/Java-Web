# 3.J2SE：J2SE轻松入门第三季  

## 1.泛型  
定义方式：``class MyTest<T>{     T var;  ...``  
泛型传的只能是引用类型  
调用方式：``MyTest<String> mt=new MyTest<String>();``  

---

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

---

## 3.文件系统操作  
### 1.显示文件的信息  
1. 创建File对象：``File file=new File("D:\\eclipse\\eclipse.exe");``  
2. 将信息存进StringBuilder中：str.append("信息内容")  
可以获得的信息：  
- 最后修改日期：file.lastModified()  
修改时间格式的方式：
```Calendar ca=Calendar.getInstance();  
ca.setTimeInMillis(file.lastModified());  
ca.getTime().toLocaleString();```  
- 文件大小：file.length()  
- 文件类型：file.isDirectory()  
- 子目录：file.listFiles()  
- 路径：file.getPath(),转化为大写并txt结尾:getPath().toUpperCase().endsWith(".TXT")  

寻找磁盘下的子目录信息，使用递归算法，可能会遇到系统不让读文件，直接忽略。  

---

## 4.字符流&字节流  
字节就是byte（8位），字符是char（16位，和具体编码有关）  
字节流可以处理所有的工作，字符流在处理字符工作的时候比较方便  
```
                           Java IO 流
                          /          \
                         /            \
                      字节流        字符流
                       /                \
                      /                  \
                     /                    \
         InputStream,OutputStream         Reader,Writer
                   /                        \
                  /                          \
                 /                            \
FileInputStream,FileOutputStream              FileReader,FileWriter
BufferedInputStream,BufferedOutputStream      BufferedReader,BufferedWriter
                                              InputStreamReader,OutputStreamWriter

    PrintStream                                   PrintWriter 
```

### 1.字节流读取文本文件（不适合使用）  
1. 新建FileInputStream对象：``FileInputStream fis=new　FileInputStream("E:\\目录\\文件.txt");``  
2. 获取文件的长度：fis.available(),然后利用获取到的长度新建字节数组:byte[] data=new byte[len]  
3. 将读取的内容存到字节数组中：fis.read(data);  
4. 最后将字节数组包装成字符串new String(data)，记得关闭字节流fis.close()  

### 2.字符流读取文本文件
1. 新建FileReader对象：``FileReader fr=new FileReader("E:\\目录\\文件.txt");``  
2. 用int型获取数据:``ch=fr.read()``  
当读出来为-1的时候说明读取结束，跟字节流的不同，字节流读出来的不能直接强转为字符，需要装配为数组再包装为字符串  
3. 将int型转化为字符，记得关闭字符流fr.close()  

### 3.字节流的方式写文件  
1. 新建FileOutputStream对象  
2. 执行write方法，将String转化为字节：``fos.write(str.getBytes())``，记得关闭字节流fos.close()　　

### 4.字符流的方式写文件   
1. 新建FileWriter对象  
2. 直接调用write方法写String(字符串)对象，记得关闭字符流fw.close()  

### 5.利用读写做复制  
读写的时候如果用字节流的话，数组的长度定义为1024。将内容读取到byte[] data字节数组中，将读取的个数存到int型中  
写出的时候，fos.write(data,0,ch),每一次写出0到ch的内容，最后一次可能就不是1024了  
提高效率的方式：使用装饰模式,BufferedOutputStream bis=new BufferedOutputStream(bis);&BufferedInputStream包装  
使用装饰模式的BufferedReader，他有readLine()方法，可以进行整行读取。

### 6.将字节流转化为字符流  
字节流转化为字符流的方式 ``OutputStreamWriter osw=new OutputStreamWriter(fos);``  

### 7.PrintStream&PrintWriter  
1. 定义输出对象：fos  
2. 将输出对象包装为PrintStream对象  
3. 调用print("输出的文字")；方法就可以输出打印文字  

**用字节流的装饰模式高效复制文件**  
```java
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("F:\\Test.txt");
		BufferedInputStream bfi=new BufferedInputStream(fis);
		FileOutputStream fos=new FileOutputStream("C:\\Test.txt");
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		byte[] arr=new byte[1024];
		int ch=0;
		while((ch=bfi.read(arr))!=-1){
			bos.write(arr, 0, ch);
		}
		bfi.close();
		bos.close();
		System.out.println("复制完毕");
	}
```

---

**进入下一章：[4.javascript](Javascript.md)**