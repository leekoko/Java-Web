# Java的基础：J2SE  

## 1.java环境  

### 1.环境的配置

所需的工具清单：Eclipse，JDK   
环境的配置过程，直接百度关键字“配置JAVA的环境变量”，这里就不再做赘述。  

### 2.环境的介绍

JDK：Java 语言的软件开发工具包，包含JRE（Java运行环境）  

---

## 2.第一个简单程序  

**输入一个成绩，判断输出是“及格”，还是“不及格”。（ps:用到Scanner）**
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

---

## 3.循环语句  
**循环打印姓名"Mike","Jenny","Johnson","Amy","Mary","Cathrine","Armstrong","Dony"并且三个一换行**
```java
public static void main(String[] args) {
//	1.将姓名存入数组
	String[] arr={"Mike","Jenny","Johnson","Amy","Mary","Cathrine","Armstrong","Dony"};
	for (int i = 0; i < arr.length; i++) {
		System.out.print(arr[i]+"\t\t");
//		2.判断一旦3的倍数，添加换行
		if((i+1)%3==0){
			System.out.println();
		}
	}
}
```

---

## 4.基本元素
### 1.基本数据类型  
一共有4大类（8种）基本的数据类型  
1. 整型:byte(8个二进制),short（2个字节）,int（4个字节）,long（8个字节）  
2. 浮点:float,double  
3. 字符:char  
4. 布尔:boolean  

### 2.名词解释  
1. 类：具有相同或相似的事物统称（抽象的概念）  
2. 对象：类的某一个特例，叫做对象（活生生的个体）  
3. 成员变量：类的属性，类所具有的特征  
4. 成员函数：类的方法，类能实施的行为  
**定义一个类Person，其属性有：name，age，其方法有：eat()，sing()。实例化类，调用方法，打印“XX岁的XX在唱歌/吃饭”**
```java
public class Main {
public static void main(String[] args) {
	Person person=new Person();
	person.age=5;
	person.name="小明";
	person.eat();
	System.out.println("-----------");
	person.sing();
}
}
//1. 定义类，声明属性，方法
class Person{
	int age;
	String name;
	void eat(){
		System.out.println(age+"岁的"+name+"正在吃饭");
	}
	void sing(){
		System.out.println(age+"岁的"+name+"正在唱歌");
	}
}
```

### 3.修饰符   
[](../images/1.jpg)  
（包package：用于对类（或其他类型）进行分类组织的机制）  







