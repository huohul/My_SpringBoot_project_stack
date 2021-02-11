package com.gxweb.service.impl;

import com.gxweb.entity.CarParking;
import com.gxweb.service.CarParkingService;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:11
 * @description ：
 * @version: 1.0
 */
public class CarParkingServiceImpl implements CarParkingService {
    private ArrayList<CarParking> carpaking = new ArrayList<>();

    {

        for (int i = 1; i <= 100; i++) {
            carpaking.add(new CarParking(i, "当前车位为空")); // 为100个车位赋予编号 和状态

        }
    }

    private static CarParkingServiceImpl carparkingservice = new CarParkingServiceImpl();

    public static CarParkingServiceImpl getCarparkingservice() {
        return carparkingservice;
    }

    public CarParkingServiceImpl() {

    }

    //停车过来的信息,车牌,还有车位,还有更改状态,利用车位编号来改变内容
    @Override
    public boolean parkcar(int id, String carid) {
        // TODO Auto-generated method stub

        for (CarParking car : carpaking) {

            if (id == car.getId() && car.getState().equals("当前车位为空")) {

                car.setState(carid);
                car.setStime();
                return true; //停车成功
            }
        }
        return false;//当前车位有车

    }

    @Override //显示所有停车系统的信息
    public void showlist() {
        // TODO Auto-generated method stub
        for (CarParking car : carpaking) {

            System.out.println(car.getId() + "\t" + car.getState());

        }
    }

    //停车时间
    public void showcartime(String carid) {
        // TODO Auto-generated method stub
        for (CarParking car : carpaking) {
            if (car.getState().equals(carid)) {
                car.setEtime();
                Date time1 = car.getEtime();//得到当前的查询时间
                Date time2 = car.getStime();//得到开始时间
                System.out.println("车位为:" + car.getId() + "车牌为" + car.getState());
                System.out.println("停车时间为:" + car.getStime());
                System.out.println("当前时间为:" + car.getEtime());
                //
                System.out.println("已经停车时间为:" + (time1.getTime() - time2.getTime()) / (1000 * 60 * 60)+"H");
            }
        }
    }

    public boolean leavecar(String carid) {
        // TODO Auto-generated method stub
        for (CarParking car : carpaking) {
            if (car.getState().equals(carid)) {
                car.setEtime();
                Date time1 = car.getEtime();//得到当前的查询时间
                Date time2 = car.getStime();//得到开始时间
                long t = (time1.getTime() - time2.getTime()) / (1000 * 60 * 60);
                System.out.println("车位为:" + car.getId() + "车牌为" + car.getState());
                System.out.println("停车时间为:" + car.getStime());
                System.out.println("当前时间为:" + car.getEtime());
                System.out.println("已经停车时间为:" + t);
                if (t <= 3) {
                    car.setMoney(0);
                    car.setAllmoney(car.getAllmoney() + car.getMoney()); //钱累加
                } else if (t > 3 && t <= 13) {
                    car.setMoney(2 * (t - 3));
                    car.setAllmoney(car.getAllmoney() + car.getMoney()); //钱累加
                } else if (t > 13) {
                    car.setMoney(20);
                    car.setAllmoney(car.getAllmoney() + car.getMoney()); //钱累加
                }
                car.setLishi(car.getLishi() + "车位:" + car.getId() + "收入金额为:" + car.getMoney() + "\n");
                System.out.println("停车费为:" + car.getMoney());
                car.setState("当前车位为空");
                return true;
            }

        }
        return false;
    }

    public void show() {
        // TODO Auto-generated method stub
        for (CarParking car : carpaking) {
            System.out.println(car.getLishi());
            return;
        }
    }

    public boolean chack(String carid) {
        for (CarParking car : carpaking) {
            if (car.getState().equals(carid)) {
                System.out.println("车辆正在停使中");
                return true;
            }

        }
        return false;
    }
}
