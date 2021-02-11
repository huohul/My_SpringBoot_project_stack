package com.gxweb.service;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:10
 * @description ：
 * @version: 1.0
 */
public interface CarParkingService {
    boolean parkcar(int id, String carid); //停车

    void showlist();//显示所有列表

    boolean leavecar(String carid); //离开
}
