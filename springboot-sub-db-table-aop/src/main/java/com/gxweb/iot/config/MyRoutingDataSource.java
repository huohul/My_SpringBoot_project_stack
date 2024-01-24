package com.gxweb.iot.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * 重写 determineCurrentLookupKey 方法，获取当前线程上绑定的路由key。Spring 在开始进行数据库操作时会通过这个方法来决定使用哪个数据库源，因此我们在这里调用上面 DbContextHolder 类的getDbType()方法获取当前操作类别。
 * <p>
 * AbstractRoutingDataSource的getConnection() 方法根据查找 lookup key 键对不同目标数据源的调用，通常是通过(但不一定)某些线程绑定的事物上下文来实现。
 * <p>
 * AbstractRoutingDataSource的多数据源动态切换的核心逻辑是：在程序运行时，把数据源数据源通过 AbstractRoutingDataSource 动态织入到程序中，灵活的进行数据源切换。
 * <p>
 * 基于AbstractRoutingDataSource的多数据源动态切换，可以实现读写分离，这么做缺点也很明显，无法动态的增加数据源。
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * determineCurrentLookupKey()方法决定使用哪个数据源、
     * 根据Key获取数据源的信息，上层抽象函数的钩子
     */
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}