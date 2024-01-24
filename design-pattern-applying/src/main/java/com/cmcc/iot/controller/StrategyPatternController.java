package com.cmcc.iot.controller;

import com.cmcc.iot.entity.domain.Receipt;
import com.cmcc.iot.service.strategypattern.IReceiptHandleStrategy;
import com.cmcc.iot.service.strategypattern.ReceiptHandleStrategyFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : CYQ
 * @version : 1.0
 * @date : Created in 2023/12/22 17:10
 * @description : 策略模式
 */
@RestController
@RequestMapping("/strategy")
public class StrategyPatternController {

    public static void main(String[] args) {
        //模拟回执
        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        //策略上下文
        for (Receipt receipt : receiptList) {
            //获取并设置策略
            IReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategy(receipt.getType());
            receiptHandleStrategy.handleReceipt(receipt);
        }
    }

}
