# http请求头信息

chrome请求的demo如下

```
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Cache-Control: max-age=0
Connection: keep-alive
Cookie: td_cookie=18446744070540366451; td_cookie=18446744070540293115; ASPSESSIONIDCQTTCBAD=DMFDLKCBAGMHFMPCEDLMIGKH
Host: kjsb.gdsrxh.com
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36
```

- Accept：客户端接收的文件类型。不同终端发送不同Accept值给服务端，服务端给不同终端不同的信息。一般情况下都无需设置。
- Accept-Encoding：客户端支持的解压缩格式，一般请求数据量小，无需设置该字段。
- Accept-Language：客户端告知服务端需要返回哪国语言（一般通过传url实现），服务端基本不解析该字段。
- Cache-Control：控制缓存，常见的如下
  - no-cache：可以缓存响应文件，但需要通过令牌（Etag）与服务器沟通缓存有效性。
  - no-store：不需要代理中的缓存文件，直接请求服务器。
  - max-age：max-age=500，接下来的500秒内，浏览器可以自主使用缓存内容，不需要向服务器发送同样的请求。
- Connection：是否会关闭网络连接。如果该值是“keep-alive”，网络连接就是持久的，不会关闭，使得对同一个服务器的请求可以继续在该连接上完成。
- Cookie：小型文本文件，用于识别用户身份，Session追踪与保存终端数据。
- Host：请求者IP与端口，服务器用于ip过滤等操作。
- Upgrade-Insecure-Requests：当值为1，表明当前浏览器支持https，支持不安全请求升级
- User-Agent：客户端的软件环境，关键内容如下：Windows NT 10.0;Win64;Chrome

