package com.cmcc.iot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cmcc.iot.service.RedisIdempotentStorage;
import com.cmcc.iot.util.IdGeneratorUtil;

import javax.annotation.Resource;

@RestController
@RequestMapping("/idGenerator")
public class IdGeneratorController {

    @Resource
    private RedisIdempotentStorage redisIdempotentStorage;

    @RequestMapping("/getIdGeneratorToken")
    public String getIdGeneratorToken() {
        String generateId = IdGeneratorUtil.generateId();
        redisIdempotentStorage.save(generateId);
        return generateId;
    }

}