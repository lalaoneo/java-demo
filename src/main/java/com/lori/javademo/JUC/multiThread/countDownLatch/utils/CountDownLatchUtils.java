package com.lori.javademo.JUC.multiThread.countDownLatch.utils;

import com.lori.javademo.common.Contains;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchUtils {

    public static CountDownLatch countDownLatch;

    static {
        countDownLatch = new CountDownLatch(Contains.countDownLatchNum);
    }
}
