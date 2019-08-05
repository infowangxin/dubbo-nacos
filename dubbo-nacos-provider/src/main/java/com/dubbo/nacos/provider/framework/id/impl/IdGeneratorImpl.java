package com.dubbo.nacos.provider.framework.id.impl;

import com.dubbo.nacos.api.enums.DnTableEnum;
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

    private final static long max = -1L ^ (-1L << 5L);

    @Value("${spring.application.name}")
    private String name;

    @Override
    public Long nextUniqueId(DnTableEnum DNTableEnum) {
        String mac = LocalUtil.getMacAddress();
        Long machineId = null;
        if (StringUtils.isNotBlank(mac)) {
            machineId = Math.abs(Long.valueOf(mac.hashCode()) % max);
        }
        Long dataCenterId = Math.abs(Long.valueOf(name.hashCode()) % max);
        log.info("# dataCenterId={},machineId={},name={},mac={}", dataCenterId, machineId, name, mac);
        Long id = localIdGenerator.nextUniqueId(dataCenterId, machineId);
        log.info("# table={},id={}", DNTableEnum, id);
        return id;
    }
}
