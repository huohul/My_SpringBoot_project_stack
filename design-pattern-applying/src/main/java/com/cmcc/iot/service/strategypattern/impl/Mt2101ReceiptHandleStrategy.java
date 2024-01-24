package com.cmcc.iot.service.strategypattern.impl;

import com.cmcc.iot.entity.domain.Receipt;
import com.cmcc.iot.service.strategypattern.IReceiptHandleStrategy;

/**
 * @author Administrator
 */
public class Mt2101ReceiptHandleStrategy implements IReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT2101:" + receipt.getMessage());
    }

}  
  


  
