package com.dubbo.nacos.provider.framework.id;

import com.dubbo.nacos.api.enums.TableEnum;
import com.dubbo.nacos.provider.ProviderApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * IdGenerator
 *
 * @author 胡桃夹子
 * @date 2019-08-04 16:32
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderApplication.class)
public class IdGeneratorTest {

    @Autowired
    private IdGenerator idGenerator;

    @Test
    public void nextUniqueId() {
        try {
            Long id = idGenerator.nextUniqueId(TableEnum.user);
            log.info("# id={}", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
