package com.ego.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-21-19:29
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //为 string 类型 key 设置序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //为 string 类型 value 设置序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //为 hash 类型 key 设置序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //为 hash 类型 value 设置序列器
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
