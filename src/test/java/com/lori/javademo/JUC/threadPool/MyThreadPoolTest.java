package com.lori.javademo.JUC.threadPool;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyThreadPoolTest {

    @Test
    public void testMyThreadPool(){
        // 创建3个线程的线程池
        MyThreadPool myThreadPool = MyThreadPool.getMyThreadPool(3);
        myThreadPool.execute(new Runnable[]{new MyTask(),new MyTask(),new MyTask()});
        myThreadPool.execute(new Runnable[]{new MyTask(),new MyTask(),new MyTask()});
        System.out.println(myThreadPool);
        myThreadPool.destory();
        System.out.println(myThreadPool);
        Assert.assertEquals(Integer.valueOf(6),Integer.valueOf(myThreadPool.getFinishedTaskNumber()));
    }
}
