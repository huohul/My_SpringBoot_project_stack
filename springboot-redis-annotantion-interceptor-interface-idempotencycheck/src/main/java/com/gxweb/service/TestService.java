package com.gxweb.service;


import com.gxweb.common.ServerResponse;

public interface TestService {
    //测试幂等
    ServerResponse testIdempotence();


}