package com.xilan.base;

import com.xilan.bengin.BaseServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/26 21:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseServiceApplication.class)
public class ConfigTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void RedisTest(){
        redisTemplate.opsForValue().set("like","123456");
    }
}
