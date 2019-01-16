package com.lori.javademo.JUC.multiThread.countDownLatch;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.lori.javademo.JUC.multiThread.countDownLatch.utils.CountDownLatchUtils;
import com.lori.javademo.common.Contains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountDownLatchTest {

    @Test
    public void testCountDownLatch() throws InterruptedException {

        ExecutorService executorService = ExecutorServiceUtil.newExecutorService();

        //准备7个线程
        for (int i = 0; i < Contains.countDownLatchNum; i++){
            executorService.submit(new CountDownLatchThread());
        }

        CountDownLatchUtils.countDownLatch.await();
        System.out.println("所有任务准备完毕");

        executorService.shutdown();
    }
}
