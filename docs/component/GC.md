# Java垃圾回收机制

## 定义垃圾

### 引用计数算法  

在对象头中分配空间保存被引用的次数，一个引用则+1，删除引用则-1。当引用次数为0时，该内容就需要被回收。

```java
String m = new String("jack");   //new String("jack");对象引用 + 1  = 1
m = null;   //new String("jack");对象引用 - 1 = 0
```

缺陷：当两个对象之间相互引用时，通过引用计数算法，GC将无法回收这两个对象

```java
ReferenceCountingGC a = new ReferenceCountingGC("objA"); //objA引用 + 1 = 1
ReferenceCountingGC b = new ReferenceCountingGC("objB"); //objB引用 + 1 = 1

a.instance = b; //objA引用 + 1 = 2
b.instance = a; //objA引用 + 1 = 2

a = null; //objA引用 - 1 = 1
b = null; //objA引用 - 1 = 1
//a和b之间还存在相互的引用
```

### 可达性分析算法

![](../images/g01.png)  

以GC Roots为起点，向下搜索，当对象与GC Roots没有链相连，说明该对象不可用。（可以解决循环依赖的问题）  

#### GC Root的对象有哪些

1. 虚拟机栈中引用的对象

   ```java
   public class StackLocalParameter {
       public StackLocalParameter(String name){}
   }
   
   public static void testGC(){
       StackLocalParameter s = new StackLocalParameter("localParameter"); //new出来对象的变量属于GC Root
       s = null;   //置空时断开引用链，localParameter对象被回收
   }
   ```

2. 方法区中类static属性引用的对象

   ```java
   public class MethodAreaStaicProperties {
       public static MethodAreaStaicProperties m;   //static属性变量属于GC Root
       public MethodAreaStaicProperties(String name){}
   }
   
   public static void testGC(){
       MethodAreaStaicProperties s = new MethodAreaStaicProperties("properties");
       s.m = new MethodAreaStaicProperties("parameter");
       s = null;  //s置空，properties对象无法与GC Root建立关系被回收
       //parameter对象依然与GC Root建立着连接，不会被回收
   }
   ```

3. 方法区中final常量引用的对象

   ```java
   public class MethodAreaStaicProperties {
       public static final MethodAreaStaicProperties m = MethodAreaStaicProperties("final");  //final属性变量属于GC Root
       public MethodAreaStaicProperties(String name){}
   }
   
   public static void testGC(){
       MethodAreaStaicProperties s = new MethodAreaStaicProperties("staticProperties");
       s = null;  //s置空，properties对象无法与GC Root建立关系被回收
       //final对象依然与GC Root建立着连接，不会被回收
   }
   ```

4. 本地方法栈native引用对象

   当使用C连接模型时，本地方法栈是C栈，C函数会进入C栈中，JVM调用指定的本地方法。

## 回收垃圾  

怎么回收垃圾 ：https://mp.weixin.qq.com/s/aA1eDYIUHuIfigTw2ffouw    





























