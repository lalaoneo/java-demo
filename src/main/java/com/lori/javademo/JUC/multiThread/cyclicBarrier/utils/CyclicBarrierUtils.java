package com.lori.javademo.JUC.multiThread.cyclicBarrier.utils;

import com.lori.javademo.common.Contains;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierUtils {

    public static CyclicBarrier cyclicBarrier;

    static {
        cyclicBarrier = new CyclicBarrier(Contains.cyclicBarrierNum);
    }
}
