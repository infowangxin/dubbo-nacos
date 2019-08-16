package com.dubbo.nacos.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * provider application
 *
 * @author 胡桃夹子
 * @date 2019-07-28 10:32
 */
@Slf4j
@EnableDubbo
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
