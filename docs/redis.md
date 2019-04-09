# Redis

### [NoSql](component/NoSql.md)

Web2.0使用Nosql，更高效方便建立数据库。

### redis基本使用

1. redis基于C语言开发，所以需要先安装gcc环境``yum install gcc c++`` 

2. 下载redis，传到服务器。redis的下载地址``https://redis.io/download``   

3. 执行编译安装，``make install PREFIX=/usr/local/redis``指定安装的目录进行安装。   

4. 启动redis

   - 前端启动

     直接``./redis-server``，默认端口是6379      

   - 后端启动

     复制源码包的``redis.conf``配置文件到redis的安装目录``cp redis.conf /usr/local/redis/bin/``，修改配置``daemonize yes``改成 yes，指定配置文件启动redis``./redis-server redis.conf``     

5. 停止redis

   进入redis-cli客户端，执行``shutdown``

### [redis集群](component/redisCluster.md)  

redis集群在业务工作中比较少用到，之前有记录到，所以整理起来。

### [redis实践](component/redisPractice.md)

redis在代码中实践可能会遇到很多问题，这里记录了之前的实践案例。









https://www.imooc.com/video/14925