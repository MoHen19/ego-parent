package com.ego.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-28-14:39
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test() {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong("order:increment", redisTemplate.getConnectionFactory());
        long increment = redisAtomicLong.getAndIncrement();
        System.out.println(increment);
    }
}
