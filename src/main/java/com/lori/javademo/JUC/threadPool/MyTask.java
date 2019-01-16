package com.lori.javademo.JUC.threadPool;

public class MyTask implements Runnable {

    private static volatile int i = 1;

    @Override
    public void run() {
        System.out.println("任务 " + (i++) + " 完成");
    }
}
