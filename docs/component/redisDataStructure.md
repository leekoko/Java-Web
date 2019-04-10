# Redis数据结构

redis有五种数据类型：字符串（String），哈希（hash），字符串列表（list），字符串集合（set），有序字符串集合（sorted set）  

#### 1.字符串（String）

字符串最常见

- 赋值命令：``set name leekoko``  
- 取值命令：``get name``
- 获取值后设置新变量：``getset name newname``  
- 删除变量：``del name``  
- 递增1：``incr num``，没有num则默认为0+1
- 同上递减：``decr num2``
- 递增递减n：``incrby num 5``,num递增5

#### 2.哈希（hash）

主要用于存储键值对

- 设置一个键值对：``hset myhash age 18``
- 设置多个键值对：``hmset myhash2 username leekoko age 24``  
- 获取一个键值对：``hget myhash username``
- 获取多个键值对：``hmget myhash username age``  





数据结构部分日后用到再做完善。(https://www.imooc.com/video/14928)