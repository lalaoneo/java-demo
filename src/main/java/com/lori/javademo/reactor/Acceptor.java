package com.lori.javademo.reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Acceptor implements Runnable {

    private int port; // server socket port
    private DeMultiplexer selector;

    private final int operateCount;

    // 代表 serverSocket
    private BlockingQueue<Source> sourceQueue = new LinkedBlockingQueue<>();

    Acceptor(DeMultiplexer selector, int port,int operateCount) {
        this.selector = selector;
        this.port = port;
        this.operateCount = operateCount;
    }

    public void newConnection(Source source) {
        sourceQueue.offer(source);
    }

    public int getPort() {
        return this.port;
    }

    public void run() {
        AtomicInteger operatedCount = new AtomicInteger(0);
        while (true) {

            // 达到执行次数需要关闭,用于UT测试
            boolean sendCloseEvent = false;

            if (0 != operateCount && operatedCount.getAndIncrement() > operateCount){
                sendCloseEvent = true;
            }

            operatedCount.getAndIncrement();

            Source tempSource = new Source();
            this.newConnection(tempSource);

            Source source = null;
            try {
                Thread.sleep(500);
                // 相当于 serverSocket.accept()
                //检索并移除此队列的头部，如果此队列不存在任何元素，则一直等待
                source = sourceQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Event acceptEvent = new Event();
            acceptEvent.source = source;
            acceptEvent.type = sendCloseEvent ? EventType.CLOSE : EventType.ACCEPT;
            selector.addEvent(acceptEvent);

            if (sendCloseEvent){
                break;
            }
        }
    }
}
