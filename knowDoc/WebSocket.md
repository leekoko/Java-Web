# WebSocket   

Z：WebSocket是HTML5一种新的协议。传统的Http只能由客户端向服务端发出请求。而如果服务端的信息连续发生改变，客户端就需要轮询请求，对资源消耗比较高。

而WebSocket就是为了解决这个问题发明的，可以让服务器主动向客户端推送信息，常见的案例是聊天室。

M：WebSocket第一步需要编写什么？

Z：获取和服务端的连接

```javascript
    var socket = new SockJS("http://localhost:5051/webSocketServer?access_token=eyJhbGci...");

    // 获取 STOMP 子协议的客户端对象
    var stompClient = Stomp.over(socket);

    // 向服务器发起websocket连接并发送CONNECT帧
    stompClient.connect(
        {},
        function connectCallback(frame) {
            // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
            setMessageInnerHTML("连接成功");
            stompClient.subscribe('/app/subscribeTest', function (response) {
                setMessageInnerHTML("已成功订阅/app/subscribeTest");
                var returnData = JSON.parse(response.body);
                setMessageInnerHTML("/app/subscribeTest 你接收到的消息为:" + returnData.responseMessage);
            });
        },
        function errorCallBack(error) {
            // 连接失败时（服务器响应 ERROR 帧）的回调方法
            setMessageInnerHTML("连接失败");
        }
    );

```

setMessageInnerHTML是将文本内容显示在前端的方法

```javascript
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }
```

在服务端实现如下

```java
    @MessageMapping("/sendTest")
    @SendTo("/topic/subscribeTest")
    public ServerMessage sendDemo(ClientMessage message) {
        String info = message.getName();
        log.info("接收到了信息" + info);   //收到的信息
        String replyMsg = "";
        if(StringUtils.isNoneEmpty(info)){
            //根据输入信息回复信息
            replyMsg = getOutByIn(info);
        }
        return new ServerMessage("你发送的消息为:" + replyMsg.toString());    //返回的信息
    }
```

这里不涉及哪个用户权限与之间的交流

