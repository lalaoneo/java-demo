package com.lori.javademo.JUC.threadlocal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadLocalTest {

    @Test
    public void testThreadLocal() {

        ThreadLocalUtil.threadLocal.set(23);

        new Thread(new ThreadLocalThread()).start();

        System.out.println("hi, i am man thread "+ThreadLocalUtil.threadLocal.get());

        Assert.assertEquals(Integer.valueOf(23),ThreadLocalUtil.threadLocal.get());
    }
}
