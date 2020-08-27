package com.sprmedis.crud;

import com.sprmedis.crud.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {
    @Resource
    RedisUtil  redisUtil;

    @Test
    public void testSelect() {
        String chat = redisUtil.getRedis("chat");
        System.out.println(chat);
        redisUtil.setRedis("sdw", "sbssb");
        System.out.println(redisUtil.getRedis("sdw"));
    }
}
