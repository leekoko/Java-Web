# Maven

## 聚合

M：什么时候会用到聚合呢？

Z：当我们多个项目进行打包，动作很机械繁琐。新建一个超级项目，将多个项目聚合到这个超级项目中，一次打包即可完成多次操作。

M：如果要将几个项目同时启动，怎么实现呢？

Z：可以使用聚合函数，聚合函数的格式如下，放在project标签下。

```xml
<packaging>pom</packaging>  
<modules>
    <module>../子模块目录名</module>
</modules>
```

被聚合的项目的packaging类型应该为pom，module标签指向子模块目录名，而非artifactId

## 继承

M：什么是继承呢？

Z：继承来源于重复使用，当子pom.xml文件用到另一个pom.xml，可以继承于该pom.xml。

M：继承如果是相同的元素会怎么处理？

Z：当元素相同，会覆盖掉。但以下元素会合并而不是覆盖

```
dependencies，developers， contributors， plugin列表（包括plugin下面的reports）， resources
```

M：继承对pom文件有什么位置要求吗？

Z：两种情况：

1. 当pom位置为父子关系，projectB可以直接写继承

   ```
   ---projectA
   	---pom.xml
   	---projectB
   		---pom.xml
   ```

   parent标签位于project标签下

   ```xml
     <parent>  
       <groupId>com.tiantian.mavenTest</groupId>  
       <artifactId>projectA</artifactId>  
       <version>1.0-SNAPSHOT</version>  
     </parent>  
   ```

2. 当pom位置非父子关系，需要添加relativePath标签指明位置

   ```
   ---projectA
   	---pom.xml
   ---projectB
   	---pom.xml
   ```

   ```xml
   <parent>  
       <groupId>com.tiantian.mavenTest</groupId>  
       <artifactId>projectA</artifactId>  
       <version>1.0-SNAPSHOT</version>  
       <relativePath>../projectA/pom.xml</relativePath>  
   </parent>  
   ```

## 依赖管理

Z：子pom继承父pom，但有时候依赖不用到，或者要用别的版本。为了解决这个问题，使用dependencyManagement标签，子pom只有使用dependency声明才能使用依赖。

dependencyManagement在project标签下，父项目pom如下：

```xml
<properties>
    <junit.version>4.1</junit.version>	<!--变量提取-->
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

子项目pom如下：

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
    </dependency>
</dependencies>
```

如果添加了版本，则会覆盖父POM的声明

## 排除依赖

M：maven会下载直接引用和间接引用（jar中的引用）的jar包，怎么不让它下载间接引用的jar包呢？

Z：使用排除依赖就可以了,在dependency的下一级中添加排除

```xml
<exclusions>
    <exclusion>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
    </exclusion>
</exclusions>
```

## 插件依赖管理

Z：插件依赖的管理跟普通依赖类似，仅是类型标签不同。

插件依赖是build的字标签，位于project标签下

```xml
<build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-source-plugin</artifactId>
          <version>${sources.plugin.verion}</version>
          <executions>
            <execution>
              <id>attach-sources</id>
              <phase>verify</phase>
              <goals>
                <goal>jar-no-fork</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.1</version>
          <configuration>
            <source>1.7</source>
            <target>1.7</target>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
```

引用插件方式如下：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-source-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## 常见问题   

M：像引入SpringCloud相关的坐标时，下载到的jar包版本是unknown。原因可能是该SpringCloud版本对应的子依赖，根本就不存在你声明的那个依赖。这个时候就需要看官方文档和进行查询了。