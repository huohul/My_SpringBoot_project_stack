package com.cmcc.iot.controller;

import com.cmcc.iot.entity.domain.Receipt;
import com.cmcc.iot.service.chainofresponsibilitypattern.IReceiptHandleChainImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : CYQ
 * @version : 1.0
 * @date : Created in 2023/12/22 17:42
 * @description : 责任链模式
 */
@RestController
@RequestMapping("/")
public class ChainofresponsibilityController {

    public static void main(String[] args) {
        //模拟回执
        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        for (Receipt receipt : receiptList) {
            //回执处理链对象
            IReceiptHandleChainImpl receiptHandleChain = new IReceiptHandleChainImpl();
            receiptHandleChain.handleReceipt(receipt);
        }
    }

}
