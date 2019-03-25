# shrio   

## 1.认证过程

如下，首先  构建securityManager环境，然后主体提交认证请求

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

授权方式如下，在添加用户时添加角色参数即可

```java
//添加账号的时候添加角色，可以添加多个        
simpleAccountRealm.addAccount("Mark","123456","admin","user");

//检查用户角色
subject.checkRoles("user");
```

## 3.IniRealm

通过IniRealm可以通过配置文件代替``simpleAccountRealm.addAccount``添加用户，具体代码如下

```ini
[users]
Mark=123456,admin,user
[roles]
admin=user:delete
```

[users]:定义用户，roles:定义角色权限

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

## 4.JdbcRealm

JdbcRealm可以代替IniRealm，通过数据库管理用户和权限信息

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

## 5.md5加密 

略（https://www.imooc.com/video/16955/0）



https://www.imooc.com/video/16959      7min





https://www.sojson.com/shiro

http://www.cnblogs.com/learnhow/p/5694876.html

https://github.com/baichengzhou/SpringMVC-Mybatis-Shiro-redis-0.2