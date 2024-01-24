package com.gxweb.iot.config;

/**
 * ThreadLocal 定义数据源切换，通过ThreadLocal将数据源绑定到每个线程上下文中，
 * ThreadLocal 用来保存每个线程的是使用读库还是写库。操作结束后清除该数据，避免内存泄漏。
 */
public class DBContextHolder {
    /**
     * ThreadLocal是一个线程内部的数据存储类，通过它可以在指定的线程中存储数据，对数据存储后，只有在当前线程中才可以获取到存储的数据，对于其他线程来说是无法获取到数据。
     * 大致意思就是ThreadLocal提供了线程内存储变量的能力，这些变量不同之处在于每一个线程读取的变量是对应的互相独立的，通过get和set方法就可以得到当前线程对应的值。
     */
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    public static void set(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("--------以下操作为master（操作）--------");
    }

    public static void slave() {
        set(DBTypeEnum.SLAVE);
        System.out.println("--------以下操作为slave（读操作）--------");
    }

    public static void clear() {
        contextHolder.remove();
    }
}