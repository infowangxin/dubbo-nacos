package com.dubbo.nacos.consumer.controller.auth;

import com.dubbo.nacos.consumer.controller.DnBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * permission controller
 *
 * @author 胡桃夹子
 * @date 2019-08-27 22:59
 */
@Slf4j
@Controller
@RequestMapping("production")
public class PermissionController extends DnBaseController {

    @GetMapping("/index")
    public String index() {
        log.info("# index");
        return "/production/index";
    }

}
