package com.xxl.job.admin.core.conf;

import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HandlerConfig {

    @Value("${dubbo.application.name}")
    private String applicationName;

    // @Value("${dubbo.registry.id}")
    // private String registryId;

    // @Value("${dubbo.registry.protocol}")
    // private String registryProtocol;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    // @Value("${dubbo.registry.port}")
    // private int registryPort;

    @Bean
    public RegistryConfig handlerRegistryConfig() {
        RegistryConfig handlerRegistryConfig = new RegistryConfig(registryAddress);
        // init dubbo registry config
        // handlerRegistryConfig.setId(registryId);
        // handlerRegistryConfig.setProtocol(registryProtocol);
        // handlerRegistryConfig.setAddress(registryAddress);
        // handlerRegistryConfig.setPort(registryPort);
        handlerRegistryConfig.setCheck(false);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("applicationName", applicationName);
        handlerRegistryConfig.setParameters(parameters);
        return handlerRegistryConfig;
    }

}
