# 日志  

## A.Logback   

M：怎么编写一个logger输出日志呢？   

Z：利用springBoot自带的logback日志，调用logger对象的对应方法即可：

1. 首先获取logger对象：

   ``private final Logger logger = LoggerFactory.getLogger(GirlServiceTest.class);``  

2. 调用其相关级别方法：

   ```java
       @Test
       public void test1(){
           logger.debug("debug..");
           logger.info("info..");
           logger.error("error..");
       }
   ```

M：为什么只输出info，error两个方法呢？

Z：系统默认info级别及以上的输出，Debug属于其以下，故不做输出。  

M：如果我要简化日志输出的内容，要怎么进行配置呢？   

Z：可以再application.yml中进行配置，例如：

```yaml
logging:
  pattern:
    console: "%d - %msg%n"
```

M：那路径，日志级别要怎么配置呢？

Z：如下所示

```yaml
logging:
  pattern:
    console: "%d - %msg%n"
  file: D:/log/logTest.log
  level:
    cn.leekoko.girl.GirlServiceTest: error
```

M：那在logback-spring.xml配置文件中怎么配置呢？

Z：首先在resource文件夹下创建logback-spring.xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <!--配置log输出位置，调用ConsoleAppender类 -->
    <appender name="consoleLog" class="ch.qos.logback.core.ConsoleAppender">
        <!--展示形式配置的类 -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <!--传值：格式为%msg%n -->
            <pattern>
                %msg%n
            </pattern>
        </layout>
    </appender>

    <root level="info">
        <appender-ref ref="consoleLog"/>
    </root>

</configuration>
```















## B.log4j的使用   

log4j分以下几个日志级别，日志级别依次升高。级别高的level会屏蔽级别低的信息。

**TRACE→DEBUG→INFO→WARNING→ERROR→FATAL→OFF。**

比如设置INFO级别，TRACE，DEBUG就不会输出，如果设置WARNING级别，则TRACE，DEBUG，INFO都不会输出。

### 1.log4j.properties文件的编写    

```properties
# Output pattern : date [thread] priority category - message   FATAL 0  ERROR 3  WARN 4  INFO 6  DEBUG 7 
LOG_DIR=D://tool/logs/
log4j.rootLogger=WARN, Console, RollingFile
# lyb:为什么其全局不用指定log
#Console
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=%d %-5p [%c{5}] - %m%n

#RollingFile
log4j.appender.RollingFile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.RollingFile.File=${LOG_DIR}/all.log
log4j.appender.RollingFile.layout=org.apache.log4j.PatternLayout
log4j.appender.RollingFile.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n

#common
log4j.logger.com.minstone.common=debug,common
log4j.appender.common=org.apache.log4j.RollingFileAppender
log4j.appender.common.MaxFileSize=10MB
log4j.appender.common.MaxBackupIndex=2
log4j.appender.common.File=${LOG_DIR}/common.log
log4j.appender.common.layout=org.apache.log4j.PatternLayout
log4j.appender.common.layout.ConversionPattern=%-d{yyyy-MM-dd HH\:mm\:ss} [%c-%L]-[%p] %m%n

#area
log4j.logger.com.minstone.modules.area=debug,area
log4j.appender.area=org.apache.log4j.RollingFileAppender
log4j.appender.area.MaxFileSize=10MB
log4j.appender.area.MaxBackupIndex=2
log4j.appender.area.File=${LOG_DIR}/area.log
log4j.appender.area.layout=org.apache.log4j.PatternLayout
log4j.appender.area.layout.ConversionPattern=%-d{yyyy-MM-dd HH\:mm\:ss} [%c-%L]-[%p] %m%n

#flow
log4j.logger.com.minstone.modules.flow=debug,flow
log4j.appender.flow=org.apache.log4j.RollingFileAppender
log4j.appender.flow.MaxFileSize=10MB
log4j.appender.flow.MaxBackupIndex=2
log4j.appender.flow.File=${LOG_DIR}/flow.log
log4j.appender.flow.layout=org.apache.log4j.PatternLayout
log4j.appender.flow.layout.ConversionPattern=%-d{yyyy-MM-dd HH\:mm\:ss} [%c-%L]-[%p] %m%n
```

1. ``LOG_DIR=D://tool/logs/``:提取共用的根目录    

2. ``log4j.rootLogger=WARN, Console, RollingFile``:设置log4j.rootLogger（所有日志）的WARNING级别为WARN，并将其赋值给（输出到）Console & RollingFile变量        

3. ``log4j.appender.Console=org.apache.log4j.ConsoleAppender``:设置Console变量为ConsoleAppender（log信息在工作台输出）        

4. 设置打印信息的样式：

   ```properties
   log4j.appender.Console.layout=org.apache.log4j.PatternLayout
   log4j.appender.Console.layout.ConversionPattern=%d %-5p [%c{5}] - %m%n
   ```

5. ``log4j.appender.RollingFile=org.apache.log4j.DailyRollingFileAppender``:设置RollingFile每日文件输出。  

6. ``log4j.appender.RollingFile.File=${LOG_DIR}/all.log``:设置输出文件的路径，为  ``LOG_DIR=D://tool/logs/``+自定义路径。   

7. ``log4j.logger.com.minstone.common=debug,common``:将com.minstone.common包赋给common。     

   log4j.logger.com.你自己.的包.=DEBUG,CONSOLE

8.  ``log4j.appender.common=org.apache.log4j.RollingFileAppender``:设置为文件达到指定大小，新增新的日志文件。    

9. ``log4j.appender.common.MaxBackupIndex=2``:设置最大记录文件数，属于``RollingFileAppender``专用。    

### 2.使用log4j  

#### 1.导入jar包   

parent项目pom.xml中添加以下代码，common项目再进行继承：

```xml
<dependency>  
	<groupId>log4j</groupId>  
	<artifactId>log4j</artifactId>  
	<version>1.2.17</version>  
</dependency>  
```

或者相似的有slf4j：      

```xml
<!-- 日志处理 -->
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.6.4</version>
</dependency>
```

#### 2.引入配置文件    

将log4j.properties文件放到web（或者controller）项目的resources文件夹的根目录下即可。    

