package com.lori.javademo.JUC.lock.reentrantReadWriteLock;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReentrantReadWriteLockTest {

    @Test
    public void testReentrantReadWriteLock() {

        /**
         * 先put数据,再get数据,get数据需要等待1秒钟才能获取
         */
        long putStart = System.currentTimeMillis();
        ReentrantReadWriteLockCache.put("lori",100,false);

        Integer result = ReentrantReadWriteLockCache.get("lori",true);

        Assert.assertEquals(Integer.valueOf(100) ,result);
        long putEnd = System.currentTimeMillis();

        System.out.println(putEnd - putStart);

        boolean putTimeGT1Flag = (putEnd-putStart)/1000 >=1;

        Assert.assertTrue(putTimeGT1Flag);

        /**
         * 先get数据,再put数据,put数据需要等待1秒钟才能获取
         */

        ReentrantReadWriteLockCache.clear();

        long getStart = System.currentTimeMillis();
        Integer result2 = ReentrantReadWriteLockCache.get("lori",false);

        Assert.assertNull(result2);

        ReentrantReadWriteLockCache.put("lori",100,true);

        Assert.assertEquals(Integer.valueOf(100) ,result);
        long getEnd = System.currentTimeMillis();

        System.out.println(getEnd - getStart);

        boolean getTimeGT1Flag = (getEnd-getStart)/1000 >=1;

        Assert.assertTrue(getTimeGT1Flag);
    }

    @Test
    public void testWriteLockDownReadLock() throws InterruptedException {
        /**
         * 测试写锁降级为读锁
         */

        // 写锁加锁
        ReentrantReadWriteLockCache.getLock().writeLock().lock();

        Thread.sleep(1000);

        // 同一线程情况下可以获取读锁
        ReentrantReadWriteLockCache.getLock().readLock().lock();

        // 释放写锁,从而降级为读锁
        ReentrantReadWriteLockCache.getLock().writeLock().unlock();

        // 最后释放读锁
        ReentrantReadWriteLockCache.getLock().readLock().unlock();
    }

    @Test
    public void testReadLockDownWriteLock() throws InterruptedException {

        /**
         * 读锁不可以降级为写锁,通过同一线程获取写锁,设置时间，达到时间会超时,进行测试
         */

        // 获取读锁
        ReentrantReadWriteLockCache.getLock().readLock().lock();

        // 尝试获取写锁,超时时间1秒钟
        boolean getWriteLockResult = ReentrantReadWriteLockCache.getLock().writeLock().tryLock(1,TimeUnit.SECONDS);

        System.out.println("获取写锁结果："+getWriteLockResult);
        Assert.assertFalse(getWriteLockResult);

        // 释放读锁,才能获取写锁
        ReentrantReadWriteLockCache.getLock().readLock().unlock();
    }
}
