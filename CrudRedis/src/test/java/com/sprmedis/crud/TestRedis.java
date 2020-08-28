package com.sprmedis.crud;

import com.sprmedis.crud.component.StringRedisUtil;
import com.sprmedis.crud.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {
    @Resource
    StringRedisUtil redisUtil;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSave(){
        UserInfo sysUser = new UserInfo("1233","李3");
        ValueOperations vOps = redisTemplate.opsForValue();
        vOps.set("zxu2", sysUser, 6000L, TimeUnit.SECONDS);
        UserInfo sysUserDB = (UserInfo) vOps.get("zxu2");
        System.out.println(sysUserDB);
    }

    @Test
    public void testStrValue(){
        ValueOperations<String, String> ops4Value = stringRedisTemplate.opsForValue();
        ops4Value.set("key", "value");
        ops4Value.set("key", "value", 1L);
        ops4Value.get("key");

    }
    @Test
    public void testSelect() {
        String chat = redisUtil.getRedis("chat");
        System.out.println(chat);
        redisUtil.setRedis("sdw", "sbssb");
        System.out.println(redisUtil.getRedis("sdw"));
    }
}
