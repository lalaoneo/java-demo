package com.lori.javademo.JUC.multiThread.cyclicBarrier;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.lori.javademo.common.Contains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CyclicBarrierTest {

    @Test
    public void testCyclicBarrier() throws InterruptedException {

        ExecutorService executorService = ExecutorServiceUtil.newExecutorService();

        for (int i = 0; i < Contains.cyclicBarrierNum; i++){
            executorService.submit(new CyclicBarrierThread());
        }

        Thread.sleep(1000);
        System.out.println("执行完毕");

        executorService.shutdown();
    }
}
