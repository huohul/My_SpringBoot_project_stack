package com.gxweb.client;

import com.gxweb.entity.Admin;
import com.gxweb.entity.User;
import com.gxweb.service.impl.AdminServiceImpl;
import com.gxweb.service.impl.CarParkingServiceImpl;
import com.gxweb.service.impl.UserServiceImpl;

import java.util.Scanner;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:03
 * @description ： 用Java开发一个停车场系统 https://mp.weixin.qq.com/s/H2X2jBT9f12cFXj6-WMeMA
 * @version: 1.0
 */
public class Client {
    public static void main(String[] args) {
        CarParkingServiceImpl carparkingservice = CarParkingServiceImpl.getCarparkingservice();
        UserServiceImpl userserviceimpl = UserServiceImpl.getUserserviceimpl();

        Scanner scanner = new Scanner(System.in);

        Menu1(carparkingservice, userserviceimpl, scanner);

    }

    private static void Menu1(CarParkingServiceImpl carparkingservice, UserServiceImpl userserviceimpl,
                              Scanner scanner) {

        while (true) {
            System.out.println("****欢迎进入停车系统****");
            System.out.println("***1.请输入您的车牌***");
            System.out.println("***2.管理员");
            System.out.println("***3.退出****");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1:
                    System.out.println("请输入您的车牌");
                    String chepai = scanner.nextLine().trim();
                    User a = userserviceimpl.login(new User(chepai));
                    if (a != null) {
                        System.out.println("车牌登记成功");
                        Menu2(carparkingservice, userserviceimpl, scanner, a);

                    } else {
                        System.out.println("车牌登记失败");
                    }
                    break;
                case 2:
                    Menu_Manger(carparkingservice, userserviceimpl);
                default:
                    break;
            }
        }

    }

    //管理员的登录注册
    private static void Menu_Manger(CarParkingServiceImpl carparkingservice, UserServiceImpl userserviceimpl) {
        AdminServiceImpl adminserviceimpl = AdminServiceImpl.getAdminSeriver();
        while (true) {
            System.out.println("1.管理员登录");
            System.out.println("2.管理员注册");
            System.out.println("3.返回上一级");
            Scanner scanner = new Scanner(System.in);
            int chose = Integer.parseInt(scanner.nextLine().trim());
            switch (chose) {
                case 1:
                    System.out.println("请输入管理员账号");
                    String name = scanner.nextLine().trim();
                    System.out.println("请输入管理员密码");
                    String password = scanner.nextLine().trim();

                    if (adminserviceimpl.login(name, password) != null) {
                        System.out.println("登录成功");
                        Menu_Manger2(carparkingservice, userserviceimpl, adminserviceimpl);//进行管理员操作
                    } else {
                        System.out.println("登录失败");
                        return;
                    }
                    break;
                case 2:
                    System.out.println("请输入注册账号");
                    name = scanner.nextLine().trim();
                    System.out.println("请输入注册密码");
                    password = scanner.nextLine().trim();
                    adminserviceimpl.register(new Admin(name, password));
                    break;
                case 3:
                    return;
                default:
                    return;

            }
        }
    }

    //管理员操作
    private static void Menu_Manger2(CarParkingServiceImpl carparkingservice, UserServiceImpl userserviceimpl,
                                     AdminServiceImpl adminserviceimpl) {

        while (true) {
            System.out.println("***欢迎进入管理员操作***");
            System.out.println("1.查看现有停车位");
            System.out.println("2.查看停车位上的车以及缴费");
            System.out.println("3.返回上一级");
            Scanner scanner = new Scanner(System.in);
            int chose = Integer.parseInt(scanner.nextLine().trim());
            switch (chose) {
                case 1:
                    carparkingservice.showlist();//显示目前所有的空的车位
                    break;
                case 2:
                    carparkingservice.show();
                    break;
                case 3:

                    return;

                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }

    private static void Menu2(CarParkingServiceImpl carparkingservice, UserServiceImpl userserviceimpl, Scanner scanner,
                              User a) {
        while (true) {
            System.out.println("欢迎车牌为:" + a.getCarid() + "车主" + "请选择您要的服务");
            System.out.println("1.停车");
            System.out.println("2.离开并缴费");
            System.out.println("3.查看停车车位");
            System.out.println("4.停车时长");
            System.out.println("0.返回上一级");
            int chose = Integer.parseInt(scanner.nextLine().trim());
            switch (chose) {
                case 1:
                    if (carparkingservice.chack(a.getCarid())) {
                        break;
                    }

                    while (true) {
                        int random = (int) (Math.random() * 10); //随机一个车位
                        if (carparkingservice.parkcar(random, a.getCarid())) {
                            System.out.println("停车成功,停车位置为:" + random);
                            break;
                        } else {
                            System.out.println("当前车位有车");
                        }
                    }

                    break;
                case 2:
                    if (carparkingservice.leavecar(a.getCarid())) {
                        System.out.println("请缴费");
                    } else {
                        System.out.println("该车未停");
                    }
                    break;
                case 3:
                    carparkingservice.showlist();//显示目前所有的空的车位
                    break;
                case 4:
                    carparkingservice.showcartime(a.getCarid());//显示停车时长
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }
}
