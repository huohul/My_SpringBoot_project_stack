package com.cmcc.iot.service.chainofresponsibilitypattern.impl;

import com.cmcc.iot.entity.domain.Receipt;
import com.cmcc.iot.service.chainofresponsibilitypattern.IReceiptHandleChain;
import com.cmcc.iot.service.chainofresponsibilitypattern.IReceiptHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 */
public class Mt6666ReceiptHandler implements IReceiptHandler {

    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if (StringUtils.equals("MT6666", receipt.getType())) {
            System.out.println("解析报文MT6666:" + receipt.getMessage());
        } else {
            //处理不了该回执就往下传递
            handleChain.handleReceipt(receipt);
        }
    }
} 