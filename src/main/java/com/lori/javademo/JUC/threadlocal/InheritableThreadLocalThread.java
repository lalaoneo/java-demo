package com.lori.javademo.JUC.threadlocal;

public class InheritableThreadLocalThread implements Runnable {

    @Override
    public void run() {
        System.out.println("-->"+InheritableThreadLocalUtil.threadLocal.get());
        InheritableThreadLocalUtil.threadLocal.set(789);
        System.out.println("++>"+InheritableThreadLocalUtil.threadLocal.get());
    }
}
