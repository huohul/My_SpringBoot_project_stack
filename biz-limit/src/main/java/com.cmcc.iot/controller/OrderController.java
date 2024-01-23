package com.cmcc.iot.controller;

import com.cmcc.iot.annotation.Idempotent;
import com.cmcc.iot.entity.Order;
import com.cmcc.iot.entity.RequestData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/saveOrder")
    @Idempotent(name = "requestData", type = RequestData.class, field = "token")
    public String saveOrder(@RequestBody RequestData<Order> requestData) {
        return "success";
    }

}