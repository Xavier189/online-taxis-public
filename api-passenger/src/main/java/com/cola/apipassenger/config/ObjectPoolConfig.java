package com.cola.apipassenger.config;

import io.lettuce.core.api.StatefulConnection;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author Xavier
 * @date 2025/6/18
 * @description 资源池配置
 */
@Configuration
public class ObjectPoolConfig {

    @Value("${redis_pool_maxIdle:8}")
    private int maxIdle;
    @Value("${redis_pool_maxActive:64}")
    private int maxActive;
    @Value("${redis_pool_maxWait:60000}")
    private int maxWait;
    @Value("${redis_pool_minIdle:4}")
    private int minIdle;

    @Bean
    public GenericObjectPoolConfig<StatefulConnection<?, ?>> genericObjectPoolConfig() {
        GenericObjectPoolConfig<StatefulConnection<?, ?>> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMaxWait(Duration.ofMillis(maxWait));
        return genericObjectPoolConfig;
    }
}
