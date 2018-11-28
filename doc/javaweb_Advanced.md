# 7.Java Web 进阶开发  

## 1.访客记录系统    

### 1.环境搭建   

1. 服务器的搭建：可以多加几个服务器，进行不同的绑定。  
2. 编写DbLib数据库连接类：  为了把数据库信息分离出来，使用context.xml配置文件

context.xml    

```xml
<Context>
<Resource
name="mytest"
type="javax.sql.DataSource"
maxActive="100"
maxIdle="30"
maxWait="5000"
username="root"
password="12345678"
driverClassName="com.mysql.jdbc.Driver"
url="jdbc:mysql://127.0.0.1:3306" />
</Context>
```

连接池：连接池是创建和管理一个连接的缓冲池的技术，这些连接准备好被任何需要它们的线程使用。  

DbLib.java   

```java
   InitialContext initCtx = new InitialContext();
   DataSource ds = (DataSource)initCtx.lookup("java:comp/env/mytest");
   return ds.getConnection();
```

initCtx.lookup("java:comp/env/mytest");    

在J2EE容器中配置JNDI参数，定义一个数据源，也就是JDBC引用参数，给这个数据源设置一个名称；然后，在程序中，通过数据源名称引用数据源从而访问后台数据库。  

3. 新建InitDB.java初始化数据库  
   1. 获取连接``Connection conn=DbLib.getConnection();``  
   2. 创建命令``st=conn.createStatement(); ``  
   3. 读取文件的内容：`` FileReader fr=new FileReader(文件路径);``,为了加速，使用BufferedReader包装  
   4. 使用循环读取的方式，获取到sql语句，用addBatch(sql)批处理sql语句  
4. 新建jsp文件调用servlet来初始化数据库   
   1. servlet文件中调用InitDB类的initialize()方法   
   2. 初始化完输出文字，过程需要异常对的捕捉，错误的话输出错误信息   

### 2.监听器  

对Web服务器上发生的事件进行处理  

1. 监听器有三大类：Servlet context，session，request三个范围  
2. 监听的方式主要有两类：lifecycle(生命周期),changes to attributes(属性的改变)   
3. 使用监听器：新建model层Visitor，新建VisitorDAO类   
   1. 在DAO中构造函数初始化连接   
   2. 进行基础的增删改查   

