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

2. 常用命令  
增：insert into studinfo values('002','李四'，'男'，20)  
删：update studinfo set name='李五' where no = '002'    
改：delete studinfo where no='002'  
查：select * from studinfo  
select name,chinese from studinfo,grade where studinfo.no==grade.no(联表查询)  

3. JDBC访问数据库  
首先下载JDBC的驱动程序  
引入到项目中（项目下：内部jar包  项目外：外部jar包）  

