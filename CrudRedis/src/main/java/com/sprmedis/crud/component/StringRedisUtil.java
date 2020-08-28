package com.sprmedis.crud.component;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class StringRedisUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String setRedis(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, 50L, TimeUnit.SECONDS);
        return "ok";
    }

    public String getRedis(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    private void otherOps() {
        // 常用方法
        stringRedisTemplate.opsForValue();// 操作字符串
        stringRedisTemplate.opsForHash();// 操作hash
        stringRedisTemplate.opsForList();// 操作list
        stringRedisTemplate.opsForSet();// 操作set
        stringRedisTemplate.opsForZSet();// 操作有序set
        // 操作字符型value数据
        ValueOperations<String, String> ops4Value = stringRedisTemplate.opsForValue();
        ops4Value.set("key", "value");
        ops4Value.set("key", "value", 1L);
        ops4Value.get("key");
        // 操作set类型value数据
        stringRedisTemplate.opsForSet().add("keySet", "1", "2", "3");
        stringRedisTemplate.opsForSet().isMember("keySet", "1");
        stringRedisTemplate.opsForSet().members("keySet");
        //
        stringRedisTemplate.expire("key", 1000L, TimeUnit.SECONDS);
        stringRedisTemplate.hasKey("key");
        stringRedisTemplate.delete("key");
        stringRedisTemplate.getExpire("key");
        stringRedisTemplate.getExpire("key", TimeUnit.SECONDS);
    }

}
