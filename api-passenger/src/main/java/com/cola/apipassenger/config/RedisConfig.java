package com.cola.apipassenger.config;

import io.lettuce.core.api.StatefulConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Xavier
 * @date 2025/6/18
 * @description redis配置
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Value("${redis_sentinel_nodes:}")
    private String nodes;
    @Value("${redis_sentinel_masterName:}")
    private String master;
    @Value("${redis_password:}")
    private String password;
    @Value("${redis_database:0}")
    private int database;


    @Bean
    @ConditionalOnProperty(name = "redis_mode", havingValue = "sentinelRedis", matchIfMissing = true)
    public RedisConnectionFactory sentinelRedisFactory(GenericObjectPoolConfig<StatefulConnection<?, ?>> genericObjectPoolConfig) {
        log.info("Redis sentinelRedis 方式连接");
        List<String> strings = Arrays.asList(nodes.split(","));
        Set<String> host = new HashSet<>(strings);
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration(master, host);
        LettucePoolingClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .clientName("api-passenger")
                .poolConfig(genericObjectPoolConfig)
                .build();

        if (StringUtils.isNotBlank(password)) {
            redisSentinelConfiguration.setPassword(password);
        }
        redisSentinelConfiguration.setDatabase(database);
        return new LettuceConnectionFactory(redisSentinelConfiguration, clientConfig);
    }


    @Bean
    @ConditionalOnProperty(name = "redis_mode", havingValue = "singleRedis")
    public RedisConnectionFactory singleRedisFactory(GenericObjectPoolConfig<StatefulConnection<?, ?>> genericObjectPoolConfig) {
        log.info("Redis singleRedis 方式连接");
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setHostName(nodes.split(":")[0]);
        redisStandaloneConfiguration.setPort(Integer.parseInt(nodes.split(":")[1]));
        LettucePoolingClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .clientName("api-passenger")
                .poolConfig(genericObjectPoolConfig)
                .build();

        if (StringUtils.isNotBlank(password)) {
            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        }
        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
