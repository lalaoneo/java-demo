package com.lori.javademo.JUC.multiThread.cyclicBarrier;

import com.lori.javademo.JUC.multiThread.cyclicBarrier.utils.CyclicBarrierUtils;
import com.lori.javademo.common.Contains;

import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierThread extends Thread{

    private static AtomicInteger integer = new AtomicInteger(Contains.ONE);

    @Override
    public void run() {
        int num = integer.getAndIncrement();
        System.out.println("第"+num+"个任务准备好");
        try {
            CyclicBarrierUtils.cyclicBarrier.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("第"+num+"开始执行");
    }
}
