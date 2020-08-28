package com.sprmedis.crud.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
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
        stringRedisTemplate.opsForValue();// 操作字符串
        stringRedisTemplate.opsForHash();// 操作hash
        stringRedisTemplate.opsForList();// 操作list
        stringRedisTemplate.opsForSet();// 操作set
        stringRedisTemplate.opsForZSet();// 操作有序set
    }

}
