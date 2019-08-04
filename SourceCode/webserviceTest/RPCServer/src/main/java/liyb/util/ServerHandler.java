package liyb.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
