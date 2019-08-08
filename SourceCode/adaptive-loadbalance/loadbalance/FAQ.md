选手可以在 issue 或者钉钉群中提问，管理员会将有共性的问题整理于此

## 00. Docker provider 启动后立即退出/无法启动

1. 手动启动 provider container

    ```shell
    docker run -ti --entrypoint=/bin/bash -p 20880:20880  registry.cn-shanghai.aliyuncs.com/aliware2019/provider
    ```
2. 运行 `docker-entrypoint.sh`

    ```shell
    docker-entrypoint.sh provider-small &
    ```
3. 查看启动日志

    ```shell
    tail -f /root/runtime/logs/stdout.log /root/runtime/logs/service-provider.log
    ```

## 01. Docker consumer 启动后立即退出/无法启动

- 首先请确定已经在本地修改了 host 文件，添加了 provider 的ip。需要注意，provider 的 ip 需要使用外网/局域网的真实ip，不能使用 localhost 或 127.0.0.0 等环回地址。
- 默认需要启动三个 provider 容器，consumer才能正常启动，如果想只启动一个 provider，需要编辑 consumer 的`com.aliware.tianchi.netty.HttpProcessHandler#buildUrls`方法，将不需要的 provider url 注释掉。

1. 手动启动 consumer container

    ```shell
    docker run -ti --entrypoint=/bin/bash -p 8087:8087  registry.cn-shanghai.aliyuncs.com/aliware2019/consumer
    ```
2. 运行 `docker-entrypoint.sh`

    ```shell
    docker-entrypoint.sh  &
    ```
3. 查看启动日志

    ```shell
    tail -f /root/runtime/logs/stdout.log /root/runtime/logs/service-consumer.log
    ```
    如果有连接 provider 不成功的错误，一般是由于 provider 的 url 配置不对或 host 没配置，修改 localhost/127.0.0.1 等地址为真实ip即可修复
    
## 02. 配置了自己的代码仓库，使用评测脚本进行镜像构建时提示 "Host key verification failed" 错误
原因: 构建镜像时，需要从阿里云仓库 clone 代码，如果自己的代码仓库是 private的，需要在 dockerfile 中配置能够下载代码的 ssh 私钥。官方默认 demo 是 public 的，所以不需要配置私钥。

详细配置方式和原理可参考[build-docker-image-clone-private-repo-ssh-key](https://vsupalov.com/build-docker-image-clone-private-repo-ssh-key/)

或者按照如下方式更改：

1. 添加 ssh 公钥到阿里云代码仓库
2. 在 `benchmarker/dockerfile/debian-jdk8-consumer` 文件夹下添加 id_rsa 文件，内容为你的私钥，以`-----BEGIN RSA PRIVATE KEY-----`开头
3. 编辑 Dockerfile ，在 `ARG user_code_address` 一行下添加如下代码

    ```shell
    RUN mkdir /root/.ssh && chmod 700 /root/.ssh
    COPY id_rsa /root/.ssh/id_rsa
    RUN touch /root/.ssh/known_hosts && \
       ssh-keyscan code.aliyun.com >> /root/.ssh/known_hosts && \
       chmod 600 /root/.ssh/id_rsa
    ```
4. 在 provider 文件夹下重复步骤 2和3 
5. 重新运行评测脚本

## 03. 执行 benchmark 失败，提示 " check signinature failed"

原因： 使用了本地构建的 `internal-service` 导致 `service-provider.jar` 的 md5 与官方镜像中的不一致。

解决方案：注释掉`benchmarker/workflow/benchmark/workflow.py` 中的43行 `self.check_signatures()`

## 04.  我是 windows 系统，想按照 localtest 搭建本机测试环境，应该怎么安装 wrk?

请参考 [windows_localtest](https://code.aliyun.com/leezepeng/localtest) , 感谢选手 @李泽言 提供教程。

## 05. 能否获取 provider 的最大线程数配置

允许

## 06. 能否使用 provider 的最大线程数作为限流值

允许。但不允许以 hardcode 的方式设定为固定值，如线程数为200/400/600的时候，固定将限流值设置为 200 。