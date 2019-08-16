package com.dubbo.nacos.provider.service.auth;

import com.alibaba.fastjson.JSON;
import com.dubbo.nacos.api.constants.DnConstants;
import com.dubbo.nacos.api.entity.auth.DnRole;
import com.dubbo.nacos.api.entity.auth.DnUser;
import com.dubbo.nacos.api.service.auth.DnAuthSerice;
import com.dubbo.nacos.provider.ProviderApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * authenticate relates test interface
 *
 * @author 胡桃夹子
 * @date 2019-08-05 22:12
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderApplication.class)
public class AuthServiceTest {

    @Autowired
    private DnAuthSerice dnAuthSerice;

    @Test
    public void findRoleByRoleCode() {
        try {
            DnRole dnRole = dnAuthSerice.findRoleByRoleCode(DnConstants.ROLE_FOR_ADMIN);
            log.info("# role={}", JSON.toJSONString(dnRole));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findUserByAccount() {
        try {
            DnUser dnUser = dnAuthSerice.findUserByAccount("vincent");
            log.info("# user={}", JSON.toJSONString(dnUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser() {
        try {
            DnUser dnUser = new DnUser();
            dnUser.setAccount("vincent");
            dnUser.setRealName("胡桃夹子");
            dnUser.setPassword("123456");
            boolean ret = dnAuthSerice.addUser(dnUser);
            log.info("# user={},ret={}", JSON.toJSONString(dnUser), ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void authorizing() {
        try {
            String roleCode = DnConstants.ROLE_FOR_ADMIN;
            String account = "vincent";
            DnRole dnRole = dnAuthSerice.findRoleByRoleCode(roleCode);
            DnUser dnUser = dnAuthSerice.findUserByAccount(account);
            boolean ret = dnAuthSerice.authorizing(dnUser.getId(), dnRole.getId());
            log.info("# ret={}", ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void login() {
        try {
            String account = "vincent";
            String password = "123456";
            DnUser dnUser = dnAuthSerice.login(account, password);
            log.info("# user={}", JSON.toJSONString(dnUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
