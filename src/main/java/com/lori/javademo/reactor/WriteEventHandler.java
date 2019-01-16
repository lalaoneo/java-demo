package com.lori.javademo.reactor;

public class WriteEventHandler extends EventHandler {

    private DeMultiplexer selector;

    public WriteEventHandler(DeMultiplexer selector) {
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {

        if (event.type == EventType.WRITE) {

            System.out.println("我写数据了,骚年"+event.source.toString());
        }
    }
}
