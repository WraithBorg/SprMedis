package com.sprmedis.crud;

import com.alibaba.fastjson.JSON;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {
    @Resource
    StringRedisUtil redisUtil;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSave() {
        UserInfo sysUser = new UserInfo("1233", "李3");
        ValueOperations vOps = redisTemplate.opsForValue();
        vOps.set("zxu2", sysUser, 6000L, TimeUnit.SECONDS);
        UserInfo sysUserDB = (UserInfo) vOps.get("zxu2");
        System.out.println(sysUserDB);
    }

    @Test
    public void testStrValue() {
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

    /**
     * 性能测试:保存用户信息
     * + 有三种方式
     * 1.利用 HMSET 和 HGET 哈希表的方式
     * 2.利用 set key value(用户信息序列化后的信息)
     * 3.利用 set key value(用户信息转成的JSON)
     * + 经过更多的数据测试发现 利用RedisTemplate的方式操作redis, 使用存JSON的方式速度最快，存哈希表的方式最慢
     */
    @Test
    public void testSaveUserInfoJSON() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            Map<String, Object> userInfo = new HashMap<>();
            String userId = "u003" + i;
            userInfo.put("name", "zxu");
            userInfo.put("age", 11);
            userInfo.put("sex", 1);
            
            List<Map> aaa = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Map<String, Object> inner = new HashMap<>();
                inner.put("AAA", "AAA");
                inner.put("AAA1", "AAA");
                inner.put("AAA2", "AAA");
                inner.put("AAA3", "AAA");
                inner.put("AAA4", "AAA");
                aaa.add(inner);
            }
            List<Map> bbb = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Map<String, Object> inner = new HashMap<>();
                inner.put("BBB", 12345);
                inner.put("BBB1", 12345);
                inner.put("BBB2", 12345);
                inner.put("BBB3", 12345);
                inner.put("BBB4", 12345);
                inner.put("BBB5", 12345);
                aaa.add(inner);
            }
            userInfo.put("aaa", aaa);
            userInfo.put("bbb", bbb);
             /*// 利用 HMSET 和 HGET 哈希表的方式 20759s
            redisTemplate.opsForHash().putAll(userId, userInfo);
            Map o = redisTemplate.opsForHash().entries(userId);
            */
            /* // 利用 set key value(用户信息序列化后的信息) 20731s
            redisTemplate.opsForValue().set(userId, userInfo);
            Map o1 = (Map) redisTemplate.opsForValue().get(userId);
            */
             // 利用 set key value(用户信息转成的JSON) 17372s
            String toJSON = JSON.toJSONString(userInfo);
            redisTemplate.opsForValue().set(userId, toJSON);
            String o = (String)redisTemplate.opsForValue().get(userId);
            Map map = JSON.parseObject(o, Map.class);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
