package com.gxweb.springbootmultidatasourcemybatispluslog4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层
 *
 * @author cyq
 * @version 1.0
 * @Date 2019/08/09 9:31
 */
//@Log4j2
@RestController
public class TestLogController {
    //Logger log = LogManager.getLogger(TestController.class);
    Logger log = LoggerFactory.getLogger(TestLogController.class);

    @GetMapping("/api/test")
    public Object hello() {
        log.trace("【TestController.class】trace level log input");
        System.out.println("【TestController.class】trace level log input");
        log.debug("【TestController.class】debug level log input");
        System.out.println("【TestController.class】debug level log input");
        log.info("【TestController.class】info level log input");
        System.out.println("【TestController.class】info level log input");
        log.warn("【TestController.class】warn level log input");
        System.out.println("【TestController.class】warn level log input");
        log.error("【TestController.class】error level log input");
        System.out.println("【TestController.class】error level log input");
        return "hello world";
    }
}
