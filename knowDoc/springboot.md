# SpringBoot    

SpringBoot为SpringMVC升级版。简化配置，很可能成为下一代的框架。   

## 1.新建项目

使用IntelliJ IDEA  ， 其破解地址为：``http://idea.lanyus.com/``    

New Project -- Spring Initializr -- 选择web ，即可创建SpringBoot项目。  

#### 启动SpringBoot项目      

运行自动生成的XXApplication类，其必须带有``@SpringBootApplication``注解，右键Run XX即可启动项目。   

```java
@SpringBootApplication
public class HellospringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(HellospringbootApplication.class, args);
	}
}
```

## 2.配置文件

### 1.配置文件.properties   

```properties
server.port=8081     
server.context-path=/girl
```

- 设置端口   
- 添加访问前缀   

### 2.配置文件.yml   

yml配置文件更加简便,推荐使用。

```properties
server:
	port:8082
	context:/girl   
```

#### 1.yml配置变量   

1. 声明配置的变量   

   ```properties
   size:B
   ```


2. 注入配置的变量

   ```java
   @Value("size")
   private String size;
   ```

#### 2.配置调用当前配置   

```properties
age:18
content:"age:${age}"
```

#### 3.yml配置对象

1. 设置配置文件

   ```properties
   girl:
   	name:koko
   	age:18
   ```

2. 注入java对象   

   ```java
   @Component
   @ConfigurationProperties(prefix="girl")
   public class GirlProperties{
     private String name;
     private Integer age;
     ...setter & getter
   }
   ```

   - 以前缀为girl的注解，将其属性注入进来。
   - @Component注解相当于:@Service,@Controller,@Repository，并下面类纳入进spring容器中管理。这样才能被下一层@Autowired注入该对象。   

#### 4.调用配置   

当配置文件需要频繁变换，将其写成两个配置文件，而主配置文件只要选好要哪一个配置文件即可。   

1. 新建两个配置文件 application-dev.yml   &  application-prod.yml

2. 在application.yml中指定调用哪一个配置文件：

   ```properties
   spring:
   	profiles:
   		active:dev
   ```

   调用dev后缀的配置文件。

3. 如果用同时使用两种配置文件，那就分别用不同启动方式启动即可。    


## 3.注解    

### 1.Controller   

1. 注解@RestController   =  @Controller + @ResponseBody 。   

### 2.RequestMapping   

1. @RequestMapping可以指定多个value： ``@RequestMapping(value={"/say","/hi"})`` 。   

2. @RequestMapping获取参数的方式：

   1. 方式一PathVariable：直接地址中传输：

      ```java
          @RequestMapping(value="/{id}/say",method = RequestMethod.GET)
          public String say(@PathVariable("id") Integer id){
              return "Hello Spring Boot:"+id;
          }
      ```

      可以将id放在前面传输：``http://localhost:8080/hello/233333/say``   

   2. 方式二RequestParam：?后面传值：  

      ```java  
          @RequestMapping(value="/say",method = RequestMethod.GET)
          public String say(@RequestParam("id") Integer id){
              return "Hello Spring Boot:"+id;
          }
      ```

      url传址方式：``http://localhost:8080/hello/say?id=110``     

      添加默认值：``(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id)``  ,如何不传id，它就会默认为0。

## 4.数据库   

### 1.环境搭建   

1. pom添加组件：

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
```

2. 配置数据库连接：

```properties
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test
    username: root
    passwod: 123456
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
```

``      ddl-auto: create``:每次都重新创建数据库，数据不保存，要保存得用update。   

``show-sql: true``:打印sql语句。   

### 2.建类生成表   

1. 新建数据库对应的POJO   


   ```java
   @Entity
   public class Girl {

       @Id
       @GeneratedValue
       private Integer id;

       private String name;

       private Integer age;

       public Girl() {    //添加无参构造方法
       }
   ...setter & getter ...
   ```

   添加数据库表``@Entity``  ，自增长``@GeneratedValue``   

2. 运行程序即可自动生成mysql表格    

### 3.实现增删改查   
















视频 5-1 实战  6min









JPA

配置数据库连接     

建类可以直接映射成表！！！设置为update可以保留数据   

直接返回Mapping请求，就可以将数据信息存到数据库

增删改查通过注解就可以直接实现

方法名有一定的规范



事务管理



​    













先看过一遍    



挑重要的地方看       

做实际案例     

查询网上资料    

做出最好的教程    

