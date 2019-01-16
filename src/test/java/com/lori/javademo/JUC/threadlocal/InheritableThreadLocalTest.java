package com.lori.javademo.JUC.threadlocal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InheritableThreadLocalTest {

    @Test
    public void testInheritableThreadLocal() {

        InheritableThreadLocalUtil.threadLocal.set(456);
        new Thread(new InheritableThreadLocalThread()).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==>"+InheritableThreadLocalUtil.threadLocal.get());
        Assert.assertEquals(Integer.valueOf(456),InheritableThreadLocalUtil.threadLocal.get());

    }
}
