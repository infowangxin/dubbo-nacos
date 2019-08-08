package com.dubbo.nacos.provider.service.auth;

import com.alibaba.fastjson.JSON;
import com.dubbo.nacos.api.constants.DnConstans;
import com.dubbo.nacos.api.entity.auth.Role;
import com.dubbo.nacos.api.entity.auth.User;
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
            Role role = dnAuthSerice.findRoleByRoleCode(DnConstans.ROLE_FOR_ADMIN);
            log.info("# role={}", JSON.toJSONString(role));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findUserByAccount() {
        try {
            User user = dnAuthSerice.findUserByAccount("vincent");
            log.info("# user={}", JSON.toJSONString(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser() {
        try {
            User user = new User();
            user.setAccount("vincent");
            user.setRealName("胡桃夹子");
            user.setPassword("123456");
            boolean ret = dnAuthSerice.addUser(user);
            log.info("# user={},ret={}", JSON.toJSONString(user), ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void authorizing() {
        try {
            String roleCode = DnConstans.ROLE_FOR_ADMIN;
            String account = "vincent";
            Role role = dnAuthSerice.findRoleByRoleCode(roleCode);
            User user = dnAuthSerice.findUserByAccount(account);
            boolean ret = dnAuthSerice.authorizing(user.getId(), role.getId());
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
            User user = dnAuthSerice.login(account, password);
            log.info("# user={}", JSON.toJSONString(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
