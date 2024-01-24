package com.cmcc.iot.service.strategypattern;

import com.cmcc.iot.service.strategypattern.impl.Mt1101ReceiptHandleStrategy;
import com.cmcc.iot.service.strategypattern.impl.Mt2101ReceiptHandleStrategy;
import com.cmcc.iot.service.strategypattern.impl.Mt8104ReceiptHandleStrategy;
import com.cmcc.iot.service.strategypattern.impl.Mt9999ReceiptHandleStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 策略工厂
 * @Auther: wuzhazha
 */
public class ReceiptHandleStrategyFactory {

    private static Map<String, IReceiptHandleStrategy> receiptHandleStrategyMap;

    private ReceiptHandleStrategyFactory() {
        receiptHandleStrategyMap = new HashMap<>();
        receiptHandleStrategyMap.put("MT2101", new Mt2101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT1101", new Mt1101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT8104", new Mt8104ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT9999", new Mt9999ReceiptHandleStrategy());
    }

    public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType) {
        if (Objects.isNull(receiptHandleStrategyMap)) {
            new ReceiptHandleStrategyFactory();
        }
        return receiptHandleStrategyMap.get(receiptType);
    }
} 