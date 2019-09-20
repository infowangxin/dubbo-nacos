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
    public String error403() {
        log.info(" 403 ");
        return "production/page_403";
    }

    @RequestMapping("/404")
    public String error404() {
        log.info(" 404 ");
        return "production/page_404";
    }

    @RequestMapping("/500")
    public String error500() {
        log.info(" 500 ");
        return "production/page_500";
    }

}
