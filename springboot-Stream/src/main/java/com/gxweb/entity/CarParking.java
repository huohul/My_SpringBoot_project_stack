package com.gxweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:05
 * @description ：
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CarParking { //车位
    public static String getLishi() {
        return lishi;
    }

    public static void setLishi(String lishi) {
        CarParking.lishi = lishi;
    }

    private int id; //停车的编号
    private double money;//钱的计算
    private String state; //当前停车状态
    private Date stime;//停车时间
    private Date etime;//停车结束
    private static double allmoney;
    private static String lishi = "";

    public void setEtime() {//停车结束时间
        etime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(this.etime);
    }

    public void setStime() {//停车开始时间
        stime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = dateFormat.format(this.stime);

    }

    public String toString() {

        return id + "\t" + money + "\t" + stime;
    }

    public CarParking(int id, String state) {

        this.id = id;
        this.state = state;
    }

    public void CarPark(int id, String carid) {
        // TODO Auto-generated method stub
        this.id = id;
        this.state = carid;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static double getAllmoney() {
        return allmoney;
    }

    public static void setAllmoney(double allmoney) {
        CarParking.allmoney = allmoney;
    }

}
