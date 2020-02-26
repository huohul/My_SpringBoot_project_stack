package com.gxweb.service.impl;

import com.gxweb.common.ServerResponse;
import com.gxweb.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }


}
