package com.sprmedis.mongo;

import com.sprmedis.mongo.entity.MoUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestMongo {

    @Resource
    private MongoTemplate mongoTemplate;
    @Test
    public void testSave() {
        // 新的数据库需要创建对应的用户
        MoUser userInfo = new MoUser(UUID.randomUUID().toString(),"大黄鸭",18);
        mongoTemplate.save(userInfo);

        Query query = Query.query(Criteria.where("name").is("大黄鸭"));
        List<MoUser> userInfos = mongoTemplate.find(query, MoUser.class);
        System.out.println(userInfos);
    }
}
