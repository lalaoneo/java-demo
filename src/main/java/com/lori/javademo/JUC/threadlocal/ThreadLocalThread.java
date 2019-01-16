package com.lori.javademo.JUC.threadlocal;

public class ThreadLocalThread implements Runnable{

    @Override
    public void run() {
        System.out.println("hi , i am thread " + ThreadLocalUtil.threadLocal.get());
    }
}
