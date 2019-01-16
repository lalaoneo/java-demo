package com.lori.javademo.JUC.threadlocal;

public class InheritableThreadLocalUtil {

    public static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
}
