# Servlet

## Servlet的生命周期

Servlet是javax.servlet包中定义的一个接口，每个Servlet都需要实现init()、service()和destroy()三个方法，由JVM负责执行：

- init()：在servlet初始化阶段调用，它的参数是ServletConfig接口的对象，初始化时能从ServletConfig中拿到配置文件内容。
- service()：每接收一个请求，就会调用service()方法。service()判断请求的类型，转发给响应的方法处理。
- destory()：销毁servlet对象时执行destory()方法，释放资源。

## Servlet处理Http请求

Servlet的引入时为了处理复杂的HTTP请求，处理过程如下：

1. Web服务器接收HTTP请求
2. Web服务器转发请求给Servlet容器
3. servlet容器判断servlet对象是否存在，不存在则init()进行创建serlvet，并保留到容器的地址空间当中
4. servlet调用service()方法处理HTTP请求，在JVM处理完数据之后将结果返回到正确的地址。  

## ServletConfig接口

### 通过ServletConfig对象获取配置信息

在web.xml中添加初始化配置

```xml
    <servlet>
        <servlet-name>ServletConfigTest</servlet-name>
        <servlet-class>com.vae.servlet.ServletConfigTest</servlet-class>
        <init-param>
            <param-name>name1</param-name>
            <param-value>value1</param-value>
        </init-param>
        <init-param>
            <param-name>encode</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </servlet>
```

类通过`` extends HttpServlet``,就可以获取到ServletConfig对象，进而拿到配置文件信息

```java
         ServletConfig config = this.getServletConfig();  
         // --获取当前Servlet中配置的初始化参数（全部获取）经常用到
         Enumeration enumration = config.getInitParameterNames();
         while(enumration.hasMoreElements()){
         	String name = (String) enumration.nextElement();
         	String value = config.getInitParameter(name);
         	System.out.println(name+":"+value);
            ...
```

### 通过ServletContext让servlet通讯

https://www.cnblogs.com/qianguyihao/p/4140877.html   后面再做完善































