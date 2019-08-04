package liyb.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RPCClient {

    public void connServer(RpcInfo info){

        try {
            Socket socket = new Socket("localhost",8089);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //告诉服务器需要调用的方法信息
            objectOutputStream.writeObject(info);

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

    public static void main(String[] args) {
        RPCClient rpcClient = new RPCClient();
        RpcInfo rpcInfo = new RpcInfo();
        rpcInfo.setPackageName("liyb.service");
        rpcInfo.setClassName("OrderService");
        rpcInfo.setMethodName("mod2");
        Object params[] = new Object[]{"leekoko",999};
        rpcInfo.setArgs(params);

        rpcClient.connServer(rpcInfo);
    }

}
