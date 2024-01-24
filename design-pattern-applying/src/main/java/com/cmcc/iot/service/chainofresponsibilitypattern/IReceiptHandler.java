package com.cmcc.iot.service.chainofresponsibilitypattern;

import com.cmcc.iot.entity.domain.Receipt;

/**
 * @Description: 抽象回执处理者接口
 * @Auther: wuzhazha
 */
public interface IReceiptHandler {

    void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain);

}  