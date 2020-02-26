package com.gxweb.controller;

import com.gxweb.annotation.ApiIdempotent;
import com.gxweb.common.ServerResponse;
import com.gxweb.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    //测试幂等
    @ApiIdempotent
    @PostMapping("testIdempotence")
    public ServerResponse testIdempotence() {
        return testService.testIdempotence();
    }

    //测试幂等1
    @ApiIdempotent
    @PostMapping("testIdempotence1")
    public ServerResponse testIdempotence1() {
        return ServerResponse.success("业务完成");
    }

}