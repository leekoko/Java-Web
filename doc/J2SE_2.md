# 2.J2SE：J2SE轻松入门第二季  

## 1.枚举&静态  
1. 枚举：枚举的定义，用enum Gender{ Male,Fenale },后面用Gender就可以点出来
2. 静态成员：表示该成员属于所有的类，到处都能访问（访问静态成员，必须使用静态方法）  
``被static修饰后的成员，在编译时由内存分配一块内存空间，直到程序停止运行才会释放，那么就是说该类的所有对象都会共享这块内存空间``  

## 2.异常  
异常是一个类的对象，所有的异常父类都为Throwable  
Throwable主要有两个子类：Error&Exception  
1. Error:JVM（虚拟机）运行异常，无法处理  
2. Exception：之一RuntimeException，表示运行时异常，可以try catch。而非RunTimeException必须得处理。添加finally表示最后一定会执行的代码块。  

>**RuntimeException**  
>1.NullPointerException（空指针异常）  
>2.IndexOutOfBoundsException（数组下标越界异常） ArrayIndexOutOfBoundsException  
>3.NumberFormatException（数据格式异常）  
>4.ClassCastException（类型转换异常）  
>5.IllegalArgumentException（非法参数异常）  
>6.ArithmeticException（算术异常）  
>7.IllegalStateException（非法语句异常）   
>  
>**非RuntimeException**  
>ClassNotFoundException（类找不到异常）  

异常也可以使用throws抛出的方式，谁调用谁处理  