package com.lori.javademo.JUC.threadPool.forkjoin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrintTaskTest {

    @Test
    public void testPrintTask() throws InterruptedException {
        PrintTask task = new PrintTask(0, 300);
        //创建实例，并执行分割任务
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(task);
        pool.shutdown();
        //线程阻塞，等待所有任务完成
        pool.awaitTermination(2, TimeUnit.SECONDS);
    }
}
