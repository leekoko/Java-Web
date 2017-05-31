# Java的基础：J2SE  

## 1.java环境  

### 1.环境的配置

所需的工具清单：Eclipse，JDK   
环境的配置过程，直接百度关键字“配置JAVA的环境变量”，这里就不再做赘述。  

### 2.环境的介绍

JDK：Java 语言的软件开发工具包，包含JRE（Java运行环境）  

## 2.简单程序  

输入一个成绩，判断输出是“及格”，还是“不及格”。（ps:用到Scanner）
```java
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		System.out.println("请输入成绩：");
		Scanner input=new Scanner(System.in);
		int num=input.nextInt();  //获取成绩
		if(num>=60){
			System.out.println("成绩及格");
		}else{
			System.out.println("不及格");
		}
	}
}
```







