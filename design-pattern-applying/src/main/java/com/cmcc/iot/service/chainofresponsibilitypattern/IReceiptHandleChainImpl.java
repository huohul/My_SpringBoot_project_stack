package com.cmcc.iot.service.chainofresponsibilitypattern;

import com.cmcc.iot.entity.domain.Receipt;
import com.cmcc.iot.util.ReflectionUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 * @Description: 责任链实现类
 * @Auther: wuzhazha
 */
public class IReceiptHandleChainImpl implements IReceiptHandleChain {
    //处理者集合
    private static List<IReceiptHandler> receiptHandlerList;

    static {
        //从容器中获取处理器对象
        receiptHandlerList = new ArrayList<>();
        //receiptHandlerList.add(new Mt2101ReceiptHandler());
        //receiptHandlerList.add(new Mt8104ReceiptHandler());

        Set<Class<?>> classList = ReflectionUtil.getClassSetBySuper(IReceiptHandler.class);
        if (!CollectionUtils.isEmpty(classList)) {
            for (Class<?> clazz : classList) {
                try {
                    receiptHandlerList.add((IReceiptHandler) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //记录当前处理者位置
    private int index = 0;

    @Override
    public void handleReceipt(Receipt receipt) {
        if (receiptHandlerList != null && receiptHandlerList.size() > 0) {
            if (index != receiptHandlerList.size()) {
                IReceiptHandler receiptHandler = receiptHandlerList.get(index++);
                receiptHandler.handleReceipt(receipt, this);
            }
        }
    }
}  