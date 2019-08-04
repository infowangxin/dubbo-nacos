package com.dubbo.nacos.provider.framework.id.impl;

import com.dubbo.nacos.api.enums.TableEnum;
import com.dubbo.nacos.common.utils.LocalUtil;
import com.dubbo.nacos.provider.framework.id.IdGenerator;
import com.dubbo.nacos.provider.framework.id.LocalIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * get unique id
 *
 * @author 胡桃夹子
 * @date 2019-08-04 14:58
 */
@Slf4j
@Component
public class IdGeneratorImpl implements IdGenerator {

    @Autowired
    private LocalIdGenerator localIdGenerator;

    @Value("${spring.application.name}")
    private String name;

    @Override
    public Long nextUniqueId(TableEnum tableEnum) {
        String mac = LocalUtil.getMacAddress();
        Long machineId = null;
        if (StringUtils.isNotBlank(mac)) {
            machineId = Math.abs(Long.valueOf(mac.hashCode()) % 3);
        }
        Long dataCenterId = Math.abs(Long.valueOf(name.hashCode()) % 2);
        log.info("# dataCenterId={},machineId={},name={},mac={}", dataCenterId, machineId, name, mac);
        Long id = localIdGenerator.nextUniqueId(dataCenterId, machineId);
        log.info("# table={},id={}", tableEnum, id);
        return id;
    }
}
