package com.cmcc.iot.service.strategypattern;

import com.cmcc.iot.entity.domain.Receipt;

/**
 * @Description: 回执处理策略接口
 * @Auther: wuzhazha
 */
public interface IReceiptHandleStrategy {

    /**
     * @param receipt
     */
    void handleReceipt(Receipt receipt);

}  