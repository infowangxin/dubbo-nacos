package com.dubbo.nacos.consumer.controller.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.nacos.api.entity.auth.DnUser;
import com.dubbo.nacos.api.service.auth.DnAuthService;
import com.dubbo.nacos.consumer.controller.DnBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo controller
 *
 * @author 胡桃夹子
 * @date 2019-08-26 22:31
 */
@Slf4j
@RestController
@RequestMapping("/dn/demo")
public class DnDemoController extends DnBaseController {

    @Reference
    private DnAuthService dnAuthService;

    @GetMapping("/findUserBy/{account}")
    public DnUser findUserByAccount(@PathVariable String account) {
        log.info("# account={}", account);
        DnUser user = dnAuthService.findUserByAccount(account);
        return user;
    }
}
