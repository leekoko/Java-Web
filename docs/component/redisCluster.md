# redis集群

首先规定一下端口号：7001 ~ 7006  

1. 进入``usr/local``目录，创建redis-cluster目录``mkdir redis-cluster``   

2. 复制redis/bin文件到/redis-cluster下，改为redis01  ``cp -r bin ../redis-cluster/redis01``   

3. 删除dump.rdb``rm -f dump.rdb``  ,rdb就是将内存的状态直接保存的快照文件

4. 复制多个redis0n文件，n从1~6``cp -r redis01/ redis06``   

5. 复制之后修改每个redis0n里的redis.conf文件``vim redis.conf ``   

   修改对应端口号

   ```
   port 7001
   ```

   如果配置yes则开启集群功能

   ```
   cluster-enabled yes
   ```

6. 复制创建集群的ruby脚本redis-trib.rb（在redis源码的src文件夹下）到redis-cluster文件夹下``cp *.rb /usr/local/redis-cluster/``   

7. 启动实例：创建脚本startall.sh``vim startall.sh``，对6个redis一并进行启动

   ```
   cd redis01
   ./redis-server redis.conf
   cd ..
   cd redis02
   ./redis-server redis.conf
   cd ..
   cd redis03
   ./redis-server redis.conf
   cd ..
   cd redis04
   ./redis-server redis.conf
   cd ..
   cd redis05
   ./redis-server redis.conf
   cd ..
   cd redis06
   ./redis-server redis.conf
   cd ..
   ```

   文件添加执行权利``chmod +x startall.sh``，+x前面可以指定u 代表用户. g 代表用户组. o 代表其他. a 代表所有. 。 没有添加则默认为a

   执行脚本``./startall.sh``,查看进程即可验证是否执行成功。 

8. 执行创建集群命令：对创建集群的ruby脚本告诉它ip

   ```
   ./redis-trib.rb create --replicas 1 192.168.0.105:7001 192.168.0.105:7002 192.168.0.105:7003 192.168.0.105:7004 192.168.0.105:7005 192.168.0.105:7006
   ```

M:但是为什么linux执行脚本要添加``./``不可呢？

Z:执行unix或linux中除了path系统变量外的目录下的命令都要加``./``。

M:为什么ruby语言用不了？

Z:要使用ruby脚本，就得安装ruby的环境。     

```
yum install ruby
yum install rubygems
```

还需要redis和ruby的接口，gem命令安装redis-3.0.0.gem

```
gem install /home/ftpuser/Desktop/redis-3.0.0.gem
```

M:为什么我刚刚连接不上服务了？

Z:首先你要看看ip地址是不是变了，而且如果你重启过服务器，redis进程是需要重开的。

M:那我集群搭建好了，要怎么做测试呢？

Z:连接任意一个结点，运行测试代码``set a 100``

```
redis01/redis-cli -h 192.168.0.105 -p 7002 -c
```

M:为什么进去redis01，连的确实7002的结点

Z:它redis01用的是他的redis-cli客户端，连接结点随便哪一个都可以。

M:那我redis想关闭的时候怎么办呢？

Z:如果没有正常关闭redis，存在内存的数据可能会丢失，关闭命令``bin/redis-cli -p 7001 shutdown``,这里我把它写成一个脚本

```
redis01/redis-cli -p 7001 shutdown
redis02/redis-cli -p 7002 shutdown
redis03/redis-cli -p 7003 shutdown
redis04/redis-cli -p 7004 shutdown
redis05/redis-cli -p 7005 shutdown
redis06/redis-cli -p 7006 shutdown
```

