package com.dubbo.nacos.provider.service.job.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.nacos.api.service.job.DnJobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Calendar;

/**
 * job examples
 *
 * @author xwang
 * @date 2019-09-23 11:16
 */
@Slf4j
@Service
public class DnJobServiceImpl implements DnJobService {

    @Override
    public String executeTask() {
        log.info("# current time={}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss:SSS").format(Calendar.getInstance()));
        return null;
    }

}
