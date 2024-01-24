package com.cmcc.iot.service.chainofresponsibilitypattern;

import com.cmcc.iot.entity.domain.Receipt;

/**
 * @Description: 责任链接口
 * @Auther: wuzhazha
 */
public interface IReceiptHandleChain {

    void handleReceipt(Receipt receipt);
}