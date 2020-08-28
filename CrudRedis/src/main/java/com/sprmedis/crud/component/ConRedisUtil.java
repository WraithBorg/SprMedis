package com.sprmedis.crud.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ConRedisUtil {
    @Resource
    private RedisTemplate redisTemplate;

    public void test(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        
    }

}
