package com.lori.javademo.reference;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReferenceTest {

    @Test
    public void testReference() {
        ReferenceUtil.setSoftReference(new SoftReference<>(new Object()));
        ReferenceUtil.setWeakReference(new WeakReference<>(new Object()));
        ReferenceUtil.setPhantomReference(new PhantomReference<>(new Object(),null));
        /**
         * obj是其后面new Object()对象的强引用,只有当obj这个引用被释放之后，
         * 对象才会被释放掉，这也是我们经常所用到的编码形式.只要引用存在，垃圾回收器永远不会回收
         */
        //强应用:
        Object obj = new Object();
        Assert.assertNotNull(obj);

        /**
         * 软引用主要用户实现类似缓存的功能，在内存足够的情况下直接通过软引用取值，
         * 无需从繁忙的真实来源查询数据，提升速度；当内存不足时，自动删除这部分缓存数据，
         * 从真正的来源查询这些数据。
         */
        //软引用：
        //非必须引用，内存溢出之前进行回收，可以通过以下代码实现
        SoftReference<Object> softReference = ReferenceUtil.getSoftReference();
        //这时候sf是对obj的一个软引用，通过sf.get()方法可以取到这个对象
        //当然，当这个对象被标记为需要回收的对象时，则返回null
        Object softReferenceObj = softReference.get();
        System.out.println("-->"+softReferenceObj);
        Assert.assertNotNull(softReferenceObj);
        softReferenceObj = null;
        System.out.println("-->"+softReference.get());

        /**
         * 弱引用是在第二次垃圾回收时回收，短时间内通过弱引用取对应的数据，可以取到，
         * 当执行过第二次垃圾回收时，将返回null。
         * 弱引用主要用于监控对象是否已经被垃圾回收器标记为即将回收的垃圾，
         * 可以通过弱引用的isEnQueued方法返回对象是否被垃圾回收器标记。
         */
        //弱引用：
        //第二次垃圾回收时回收，可以通过如下代码实现
        WeakReference<Object> weakReference = ReferenceUtil.getWeakReference();
        //有时候会返回null
        Object weakReferenceObj = weakReference.get();
        System.out.println("-->"+weakReferenceObj);
        Assert.assertNotNull(weakReferenceObj);
        weakReferenceObj = null;
        //返回是否被垃圾回收器标记为即将回收的垃圾
        System.out.println("-->"+weakReference.isEnqueued());
        Assert.assertFalse(weakReference.isEnqueued());

        /**
         * 虚引用是每次垃圾回收的时候都会被回收，通过虚引用的get方法永远获取到的数据为null，
         * 因此也被成为幽灵引用。
         * 虚引用主要用于检测对象是否已经从内存中删除。
         */
        //虚引用：
        //垃圾回收时回收，无法通过引用取到对象值，可以通过如下代码实现
        PhantomReference<Object> phantomReference = ReferenceUtil.getPhantomReference();
        //永远返回null
        System.out.println("-->"+phantomReference.get());
        Assert.assertNull(phantomReference.get());
        //返回是否从内存中已经删除
        System.out.println("-->"+phantomReference.isEnqueued());
        Assert.assertFalse(phantomReference.isEnqueued());
    }
}
