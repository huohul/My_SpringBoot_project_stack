package com.cmcc.iot.controller;

import com.cmcc.iot.entity.domain.Receipt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : CYQ
 * @version : 1.0
 * @date : Created in 2023/12/22 17:50
 * @description :
 */
public class ReceiptBuilder {

    public static List<Receipt> generateReceiptList() {
        //直接模拟一堆回执对象
        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(new Receipt("我是MT2101回执喔", "MT2101"));
        receiptList.add(new Receipt("我是MT1101回执喔", "MT1101"));
        receiptList.add(new Receipt("我是MT8104回执喔", "MT8104"));
        receiptList.add(new Receipt("我是MT9999回执喔", "MT9999"));
        receiptList.add(new Receipt("我是MT6666回执喔", "MT6666"));
        //......
        return receiptList;
    }
}
