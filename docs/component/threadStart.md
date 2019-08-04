# 线程启动源码分析

线程启动类调用代码如下：

```java
public class ThreadTest implements Runnable{

    Integer count = 0;

    @Override
    public void run() {
        count ++;
        System.out.println(count);
    }

}
```

```java
    @Test
    public void numGo(){
        //2 实现runable接口
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
    }
```

Runnable接口源码如下：

```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```

- ``@FunctionalInterface``是JDK8引入的新注解，用于编译级错误检查，当接口不属于函数式接口（有且仅有一个抽象方法，但是可以有多个非抽象方法的接口）定义时就会报错。

Thread类初始化主要源码如下：

```java
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext acc) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        this.name = name.toCharArray();

        Thread parent = currentThread();
        SecurityManager security = System.getSecurityManager();
        if (g == null) {
            /* Determine if it's an applet or not */

            /* If there is a security manager, ask the security manager
               what to do. */
            if (security != null) {
                g = security.getThreadGroup();
            }
            ...
```

name是表示Thread的名字，target表示要执行的任务。