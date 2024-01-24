package com.cmcc.iot.service.chainofresponsibilitypattern.impl;

import com.cmcc.iot.entity.domain.Receipt;
import com.cmcc.iot.service.chainofresponsibilitypattern.IReceiptHandleChain;
import com.cmcc.iot.service.chainofresponsibilitypattern.IReceiptHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 */
public class Mt2101ReceiptHandler implements IReceiptHandler {

    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if (StringUtils.equals("MT2101", receipt.getType())) {
            System.out.println("解析报文MT2101:" + receipt.getMessage());
        } else {   //处理不了该回执就往下传递
            handleChain.handleReceipt(receipt);
        }
    }
}  
