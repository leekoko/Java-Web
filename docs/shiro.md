# shrio   

## 1.认证过程

shiro最简单的登陆过程

```java
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("Mark","123456");
    }

    @Test
    public void testAuthentication(){
        //1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
		//创建token
        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        //登录
        subject.login(token);
        System.out.println("subject.isAuthenticated:"+subject.isAuthenticated());
		//登出
        subject.logout();
        System.out.println("subject.isAuthenticated:"+subject.isAuthenticated());
    }
```

## 2.授权过程

shiro最简单的授权方式，在addAccount方法中加入角色参数

```java
//添加账号的时候添加角色，可以添加多个        
simpleAccountRealm.addAccount("Mark","123456","admin","user");

//检查用户角色
subject.checkRoles("user");
```

## 3.配置文件管理用户、角色：IniRealm

通过IniRealm可以通过配置文件代替``simpleAccountRealm.addAccount``添加用户，具体代码如下

```ini
[users]
Mark=123456,admin,user
[roles]
admin=user:delete
```

```java
@Test
public void testIniRealm(){
    //通过IniRealm导入账号
    IniRealm iniRealm = new IniRealm("classpath:user.ini");
    DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
    defaultSecurityManager.setRealm(iniRealm);
    //2.主体提交认证请求
    SecurityUtils.setSecurityManager(defaultSecurityManager);
    Subject subject = SecurityUtils.getSubject();
    //创建token
    UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
    //登录
    subject.login(token);
    System.out.println("subject.isAuthenticated:"+subject.isAuthenticated());

    //检查用户角色
    subject.checkRoles("user");
    //检查是否有删除用户权限
    subject.checkPermission("user:delete");
}
```

## 4.数据库管理用户、角色：JdbcRealm

JdbcRealm可以代替IniRealm，通过数据库管理用户和权限信息。

根据shiro的JdbcRealm类中的sql语句建表，默认表结构如下：

```
users				用户表(username,password)
user_roles			角色表(username,role_name)
roles_permissions	角色权限表(role_name,permission)
```

以下为jdbcRealm的使用方式

```java
    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/lees");
        dataSource.setUsername("root");
        dataSource.setPassword("mm");
    }

    /**
     * jdbcRealm测试
     */
    @Test
    public void testJdbcRealm(){
		//新建JdbcRealm
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //select查询需要打开开关
        jdbcRealm.setPermissionsLookupEnabled(true);

        //构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        //主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken("leekoko","123456");
        //登录
        subject.login(token);
        System.out.println("subject.isAuthenticated3:"+subject.isAuthenticated());

        subject.checkPermission("user:update");
    }
```

## 5.自定义realm

### 自定义:认证&授权

```java
public class CustomRealm extends AuthorizingRealm{

    Map<String,String> userMap = new HashMap<String, String>(16);
    {
        userMap.put("leekoko","123456");
        super.setName("customRealm");
    }

    /**
     * 自定义角色权限
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String)principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRoleByUserName(userName);
        Set<String> permission = getPressionsByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permission);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPressionsByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    private Set<String> getRoleByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    /**
     * 自定义认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //获取凭证
        String password = getPasswordByUsername(userName);
        if(password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("leekoko",password,"customRealm");
        return authenticationInfo;
    }

    /**
     * 模拟数据库
     * @param userName
     * @return
     */
    private String getPasswordByUsername(String userName) {
        return userMap.get(userName);
    }

}
```

### 测试Realm

```java
    public void testAuth(){
        CustomRealm customRealm = new CustomRealm();
        //构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);
        //主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("leekoko","123456");
        subject.login(token);
        //登陆校验
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        //权限校验
        subject.checkRole("admin");
        subject.checkPermissions("user:add","user:delete");
    }
```

## 6.md5加密

### 普通加密

如果数据库存储的是md5加密的密文，那么在使用CustomRealm的时候，设定以下代码：

```java
...
  		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //md5加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1); //hash迭代次数
        customRealm.setCredentialsMatcher(matcher);

        defaultSecurityManager.setRealm(customRealm);
...
```

### 加盐加密

如果密文是通过加盐的，那只要在``doGetAuthenticationInfo()``的实现方法中添加以下加盐代码即可

```java
...
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("leekoko",password,"customRealm");
		//加盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("Mark"));

        return authenticationInfo;
...
```

## 7.Shiro集成Spring

### 1.创建[SpringMVC](docs/SpringMVC.md)项目

### 2.[spring-shiro案例](component/shiroSpringDemo.md)  

Shiro集成Spring需要不少的配置，在这里做简单的记录。  

### 3. ContextLoaderListener

ContextLoaderListener监听器的作用就是启动Web容器时，装配配置信息。默认装配的是``/WEB-INF/applicationContext.xml``下的applicationContext

而我们这里制定了装配的配置文件spring.xml

```xml
	<!-- 加载spring容器 -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:spring/spring.xml</param-value>
	</context-param>
```

### 4.DelegatingFilterProxy

DelegatingFilterProxy是对于servlet filter的代理，通过这个类有两个好处：

- spring容器来管理servlet filter的生命周期
- 容器的实例通过读取配置文件注入

使用方式如下：

1. 在web.xml中配置

   ```xml
   	<filter>
   		<filter-name>shiroFilter</filter-name>
   		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
   	</filter>
   	<filter-mapping>
   		<filter-name>shiroFilter</filter-name>
   		<url-pattern>/*</url-pattern>
   	</filter-mapping>
   ```

2. 在Spring的配置文件中(spring.xml)，配置具体的Filter类的实例(需要保持spring.xml配置文件中的bean的id跟web.xml中的filter-name一样)

   ```xml
   	<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
           <property name="securityManager" ref="securityManager"/> <!--注入实例 -->
           <property name="loginUrl" value="login.html"/>
           <property name="unauthorizedUrl" value="403.html"/>
           <property name="filterChainDefinitions">
               <value>
                   /login.html = anon
                   /subLogin = anon
                   /* = authc
               </value>
           </property>
       </bean>
   ```

   ​









[https://www.imooc.com/video/16961](https://www.imooc.com/video/16961)

[https://juejin.im/post/5ab1b969f265da239376f1a6](https://juejin.im/post/5ab1b969f265da239376f1a6)   shiro简介

https://www.sojson.com/shiro   shiro用户管理demo

http://www.cnblogs.com/learnhow/p/5694876.html  shiro简介

