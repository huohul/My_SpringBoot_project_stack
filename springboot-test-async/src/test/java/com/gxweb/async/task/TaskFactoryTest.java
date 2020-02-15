package com.gxweb.async.task;


import com.gxweb.async.SpringBootDemoAsyncApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/14 14:03
 * @description ：测试工厂
 * @version: 1.0
 */
@Slf4j
public class TaskFactoryTest extends SpringBootDemoAsyncApplicationTests {
    @Autowired
    private TaskFactory taskFactory;


    /**
     * 测试异步任务
     */
    @Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        //先创建 后异步执行 不可创建后直接get()  不然又成了同步
        Future<Boolean> task1 = taskFactory.asyncTask1();
        Future<Boolean> task2 = taskFactory.asyncTask2();
        Future<Boolean> task3 = taskFactory.asyncTask3();
        //调用异步get（） 阻塞主线程
        task1.get();
        task2.get();
        task3.get();
        long end = System.currentTimeMillis();


        log.info("异步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }

    @Test
    public void tastTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        taskFactory.task1();
        taskFactory.task2();
        taskFactory.task3();
        long end = System.currentTimeMillis();
        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));

    }

}
