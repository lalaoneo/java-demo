package com.lori.javademo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceUtil {

    public static SoftReference<Object> softReference;

    public static WeakReference<Object> weakReference;

    public static PhantomReference<Object> phantomReference;

    public static SoftReference<Object> getSoftReference() {
        return softReference;
    }

    public static void setSoftReference(SoftReference<Object> param) {
        softReference = param;
    }

    public static WeakReference<Object> getWeakReference() {
        return weakReference;
    }

    public static void setWeakReference(WeakReference<Object> param) {
        weakReference = param;
    }

    public static PhantomReference<Object> getPhantomReference() {
        return phantomReference;
    }

    public static void setPhantomReference(PhantomReference<Object> param) {
        phantomReference = param;
    }
}
