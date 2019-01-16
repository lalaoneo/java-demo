package com.lori.javademo.JUC.multiThread.countDownLatch;

import com.lori.javademo.JUC.multiThread.countDownLatch.utils.CountDownLatchUtils;
import com.lori.javademo.common.Contains;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchThread implements Runnable {

    private static AtomicInteger integer = new AtomicInteger(Contains.ONE);

    private static int count = 0;

    @Override
    public void run() {
        System.out.println("第"+integer.getAndIncrement()+"个任务准备好");
        try {
            Thread.sleep(new Random().nextInt(1000));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        count++;
        System.out.println(count+"<--");
        CountDownLatchUtils.countDownLatch.countDown();
    }
}
