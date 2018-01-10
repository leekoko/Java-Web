# log4j的使用   

log4j分以下几个日志级别，日志级别依次升高。级别高的level会屏蔽级别低的信息。

**TRACE→DEBUG→INFO→WARNING→ERROR→FATAL→OFF。**

比如设置INFO级别，TRACE，DEBUG就不会输出，如果设置WARNING级别，则TRACE，DEBUG，INFO都不会输出。

## 1.log4j.properties文件的编写    

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

## 2.使用log4j

### 1.导入jar包   

parent项目pom.xml中添加以下代码，common项目再进行继承：

```xml
<dependency>  
	<groupId>log4j</groupId>  
	<artifactId>log4j</artifactId>  
	<version>1.2.17</version>  
</dependency>  
```

#### 或者相似的有slf4j：      

```xml
<!-- 日志处理 -->
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.6.4</version>
</dependency>
```

### 2.引入配置文件    

将log4j.properties文件放到web（或者controller）项目的resources文件夹的根目录下即可。   
