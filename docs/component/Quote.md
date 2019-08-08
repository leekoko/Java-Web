loading





引用1.强引用  

Z：引用类型主要对 可达性 和 垃圾回收 有影响

### 引用1.强引用  

实例：普通引用。

回收：超过作用域或赋值为null，可以被垃圾回收。

实现：Object obj = new Object();

### 引用2.软引用

实例：内存敏感的缓存。

回收：OOM之前，清除指向对象

转换：可以访问对象，重新指向强引用。

实现：SoftReference

引用队列场景：垃圾回收对象时，软引用加入队列。poll()方法可以获取Reference对象。

### 引用3.弱引用

实例：缓存。非强制性映射，还在就使用它，否则实例化。

回收：扫描内存区域，发现就回收

转换：可以访问对象，重新指向强引用。

实现：WeakReference

### 引用4.幻象引用   

实例：不能访问对象，只是提供对象finalize时做某事的机制。可以在对象被回收前做一些事（收到通知）

实现：PhantomReference

引用队列场景：垃圾回收对象时，发现虚引用，回收之前加入队列。

```java
ReferenceQueue queue = new ReferenceQueue (); 
PhantomReference pr = new PhantomReference (object, queue); 
```





举例文章：https://blog.csdn.net/mazhimazh/article/details/19752475

极客时间：https://time.geekbang.org/column/article/6970