package com.lori.javademo.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DeMultiplexer {

    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
    private final Object lock = new Object();

    List<Event> select() {
        return select(0);
    }

    List<Event> select(long timeout) {
        if (timeout > 0) {
            if (eventQueue.isEmpty()) {
                synchronized (lock) {
                    if (eventQueue.isEmpty()) {
                        try {
                            lock.wait(timeout);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
        List<Event> events = new ArrayList<>();
        // 移除此队列中所有可用的元素，并将它们添加到给定 collection 中
        eventQueue.drainTo(events);
        return events;
    }

    public void addEvent(Event e) {
        boolean success = eventQueue.offer(e);
        if (success) {
            synchronized (lock) {
                lock.notify();
            }

        }
    }
}
