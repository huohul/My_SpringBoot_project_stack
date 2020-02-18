package com.gxweb.common.util.error;

import java.sql.SQLException;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/19 0:32
 * @description ：随机抛出异工具类
 * @version: 1.0
 */
public class RandomException {
    /**
     * 随机抛出异常.
     */
    public static void randomException(Double Probability) throws Exception {
        Exception[] exceptions = { //异常集合
                new NullPointerException(),
                new ArrayIndexOutOfBoundsException(),
                new NumberFormatException(),
                new SQLException()};
        //发生概率
        double probability = Probability;  //0.75
        if (Math.random() < probability) {
            //情况1：要么抛出异常
            throw exceptions[(int) (Math.random() * exceptions.length)];
        } else {
            //情况2：要么继续运行
        }
    }
}
