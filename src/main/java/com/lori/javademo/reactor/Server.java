package com.lori.javademo.reactor;

public class Server {
    //分离器
    DeMultiplexer selector = new DeMultiplexer();
    //事件分发器
    EventDispatcher eventLooper = new EventDispatcher(selector);
    Acceptor acceptor;

    Server(int port,int operateCount) {
        acceptor = new Acceptor(selector, port,operateCount);
    }

    public void start() {
        eventLooper.registerEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
        eventLooper.registerEventHandler(EventType.READ, new ReadEventHandler(selector));
        eventLooper.registerEventHandler(EventType.WRITE, new WriteEventHandler(selector));
        new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
        eventLooper.handleEvents();
    }
}
