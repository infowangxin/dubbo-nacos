package com.dubbo.nacos.consumer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Redis config
 *
 * @author 胡桃夹子
 * @date 2019-09-07 16:27
 */
@Data
@Configuration
@PropertySource(value = "application.yml", factory = CustomPropertySourceFactory.class)
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;
}
