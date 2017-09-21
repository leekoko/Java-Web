# JDBC的使用   

## 1.API的java类  

1. Driver接口：通过操作Driver接口可对驱动程序操作  

2. DriverManager接口：Driver的管理类，用Class.forName注册驱动程序，通过getConnection（三个参数dbUrl，用户名，密码）建立物理链接。  

   - *dbUrl的组成：jdbc(协议):mysql(子协议)://10.164.172:3306/cloud_study(子名称)*  

   ![](../images/db01.png)

   （常见jdbc URL的格式）  

   - Connection物理链接作用  

   1. 创建Statement对象

   Statement是sql容器，可以进行sql语句操作：``ResultSet rs=stmt.sescuteQuery("select userName from user");``   

   ResultSet对象是sql查询的结果，ResultSet的方法有：下一个，上一个，首个，尾个，指定个。  获取对象的方法：getString(列名),getInt(列名),getObject(列名)   

## 2.jdbc的构建   

1. 构建的大概步骤  

![](../images/db02.png)  





第2视频：1min   eclipse环境下编写



