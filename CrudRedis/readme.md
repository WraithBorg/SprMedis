# Redis curd操作
### Redis存放数据类型
> Redis支持5种数据类型,但是redis存放的是二进制数据,也就是字节数组(byte[])
这些字节数据是没有类型的,使用合理的格式解码后,可以变成一个字符串,整数,或对象,此时才有数据类型
所以只要能转化成字节数组(byte[])的,都可以存放到redis里面,不管是字符串,数字,对象,图片,声音,视频,文件等
因此Redis的String并不是指字符串,redis里的key和value都是byte数组

### Develop Standard
1.key名设计要以业务名(或数据库名)为前缀(防止key冲突)，用冒号分隔,业务名:表名:id,`ugc:video:1`
2.不要包含空格、换行、单双引号以及其他转义字符
3.value设计
+ string类型控制在10KB以内，hash、list、set、zset元素个数不要超过5000
+ 非字符串的bigkey，不要使用del删除，使用hscan、sscan、zscan方式渐进式删除
+ 防止bigkey过期时间自动删除造成阻塞
