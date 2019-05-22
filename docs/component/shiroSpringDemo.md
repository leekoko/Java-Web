# spring-shiro案例

Shiro集成Spring

1. 引入shiro的pom依赖

   ```xml
           <dependency>
               <groupId>org.apache.shiro</groupId>
               <artifactId>shiro-core</artifactId>
               <version>1.4.0</version>
           </dependency>

           <dependency>
               <groupId>org.apache.shiro</groupId>
               <artifactId>shiro-spring</artifactId>
               <version>1.4.0</version>
           </dependency>

           <dependency>
               <groupId>org.apache.shiro</groupId>
               <artifactId>shiro-web</artifactId>
               <version>1.4.0</version>
           </dependency>
   ```

2. web.xml配置shiro拦截

   ```xml
   	<filter>
   		<filter-name>shiroFilter</filter-name>
   		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
   	</filter>
   	<filter-mapping>
   		<filter-name>shiroFilter</filter-name>
   		<url-pattern>/*</url-pattern>
   	</filter-mapping>

   	<!-- 加载spring容器 -->
   	<context-param>
   		<param-name>contextConfigLocation</param-name>
   		<param-value>classpath:spring/spring.xml</param-value>
   	</context-param>
   	<!--ContextLoaderListener监听器 -->
   	<listener>
   		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
   	</listener>
   ```

3. spring.xml配置shiro拦截信息

   ```xml
   <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
           <property name="securityManager" ref="securityManager"/>
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
       <!--创建securityManager对象-->
       <bean class="org.apache.shiro.web.mgt.DefaultWebSecurityManager" id="securityManager">
           <property name="realm" ref="realm"/>
       </bean>

       <bean class="cn.leekoko.controller.realm.CustomRealm" id="realm">
           <property name="credentialsMatcher" ref="credentialsMatcher"/>
       </bean>

       <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher" id="credentialsMatcher">
           <property name="hashAlgorithmName" value="md5"/>
           <property name="hashIterations" value="1"/>
       </bean>
   ```

4. Controller调用登陆逻辑

   ```java
       @RequestMapping(value = "/subLogin",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
       @ResponseBody
       public String subLogin(User user){
           Subject subject = SecurityUtils.getSubject();
           UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
           try {
               subject.login(token);
           }catch (AuthenticationException e){
               return e.getMessage();
           }
           if(subject.hasRole("admin")){
               return "有admin权限";
           }
           return "无admin权限";
       }
   ```



