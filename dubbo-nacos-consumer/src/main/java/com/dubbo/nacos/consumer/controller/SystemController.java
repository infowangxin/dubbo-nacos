package com.dubbo.nacos.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 胡桃夹子
 * @date 2019-08-05 22:38
 */
@Slf4j
@Controller
public class SystemController {

    @RequestMapping("/403")
    public String unauthorizedRole() {
        log.info("------没有权限-------");
        return "403";
    }

}
