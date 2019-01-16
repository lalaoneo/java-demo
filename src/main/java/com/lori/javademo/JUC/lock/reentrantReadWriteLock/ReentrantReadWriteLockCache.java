package com.lori.javademo.JUC.lock.reentrantReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockCache {

    private static Map<String,Integer> map = new HashMap<>();

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static Integer get(String key,boolean immediatelyFlag){
        lock.readLock().lock();
        try {
            if (!immediatelyFlag){
                Thread.sleep(1000);
            }
            return map.get(key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public static Integer put(String key,Integer value,boolean immediatelyFlag){
        lock.writeLock().lock();
        try {
            if (!immediatelyFlag){
                Thread.sleep(1000);
            }
            return map.put(key,value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void clear(){
        lock.writeLock().lock();
        try {
            map.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
        lock.writeLock().unlock();
    }

    public static ReentrantReadWriteLock getLock(){
        return lock;
    }
}
