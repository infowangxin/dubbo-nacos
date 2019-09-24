package com.dubbo.nacos.provider.service.job;

import com.dubbo.nacos.api.service.job.DnJobService;
import com.dubbo.nacos.provider.ProviderApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * job examples junit test
 *
 * @author xwang
 * @date 2019-09-23 11:24
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderApplication.class)
public class DnJobServiceTest {

    @Autowired
    private DnJobService dnJobService;

    @Test
    public void executeTask() {
        dnJobService.executeTask();
    }

}
