# webservice

[模拟webservice代码](../SourceCode)   

代码解释如下

## 声明注解   

```java
@Target(ElementType.TYPE)    //指明注解的位置
@Retention(RetentionPolicy.RUNTIME)    //加入编译到运行时期
public @interface Liyb {

}
```

声明了注解之后，就可以对添加注解的类进行筛选

```java
...
                    if(clazz.isAnnotationPresent(Liyb.class)){
                    String clazzName = clazz.getName();
...
```

## 服务端等待请求

```java
public class RPCRequest {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    public void provideServer(){

        try {
            ServerSocket serverSocket = new ServerSocket(8089);

            while (true){
                System.out.println("wait client conn");
                Socket socket = serverSocket.accept();
                //等待信息传输
                System.out.println("client success conn");
                executorService.execute(new ServerHandler(socket));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```

编写ServerSocket监听8090，等待端口的信息接收

## 服务端发送信息

```java
    public void connServer(RpcInfo info){

        try {
            Socket socket = new Socket("localhost",8089);
            //指定数据流的输出位置
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //告诉服务器需要调用的方法信息
            objectOutputStream.writeObject(info);
			//获取socket返回的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String result = (String) objectInputStream.readObject();
            System.out.println("Client返回值：" + result);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
```

针对8089传输RpcInfo对象，RpcInfo对象需要序列化。继承``Serializable``，并且添加``serialVersionUID``。（不添加的话，接口会默认生成一个serialVersionUID，默认的serialVersinUID对于class的细节非常敏感，反序列化时可能会导致InvalidClassException这个异常。）

## 服务端处理接收的信息

```java
ExecutorService executorService = Executors.newFixedThreadPool(10);
...
executorService.execute(new ServerHandler(socket));
```

接收到信息之后，使用线程池处理socket信息

```java
public class ServerHandler implements Runnable {

    Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("run-----");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcInfo rpcInfo = (RpcInfo) objectInputStream.readObject();
            System.out.println(rpcInfo.getClassName()+"服务端拿到的信息");

            String packageName = rpcInfo.getPackageName();

            String className = rpcInfo.getClassName();

            Class clazz = Class.forName(packageName + "." + className);
            Object targetObject = clazz.newInstance();
            String methodName = rpcInfo.getMethodName();

            Object params[] = rpcInfo.getArgs();

            Class types[] = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                types[i] = params[i].getClass();
            }

            Method targetMethod = clazz.getDeclaredMethod(methodName, types);
            Object result = targetMethod.invoke(targetObject, params);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(result);
            objectOutputStream.close();
            objectInputStream.close();
...
```

从socket中拿到RpcInfo对象，通过拿到的对象信息反序列化，调用指定的方法。

