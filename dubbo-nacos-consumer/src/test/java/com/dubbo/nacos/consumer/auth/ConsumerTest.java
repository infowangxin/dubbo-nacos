package com.dubbo.nacos.consumer.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dubbo.nacos.api.constants.DnConstants;
import com.dubbo.nacos.api.entity.auth.DnRole;
import com.dubbo.nacos.api.service.auth.DnAuthService;
import com.dubbo.nacos.consumer.ConsumerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * consumer junit test
 * @author 胡桃夹子
 * @date 2019-10-09 14:23
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class ConsumerTest {

    @Reference(interfaceClass = DnAuthService.class, version = "1.0")
    private DnAuthService dnAuthService;

    @Test
    public void findRoleByRoleCode() {
        try {
            DnRole dnRole = dnAuthService.findRoleByRoleCode(DnConstants.ROLE_FOR_ADMIN);
            log.info("# role={}", JSON.toJSONString(dnRole));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
