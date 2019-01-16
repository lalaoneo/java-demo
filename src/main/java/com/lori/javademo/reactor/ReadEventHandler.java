package com.lori.javademo.reactor;

public class ReadEventHandler extends EventHandler {

    private DeMultiplexer selector;

    public ReadEventHandler(DeMultiplexer selector) {
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {
        if (event.type == EventType.READ) {

            System.out.println("我读数据了,骚年"+event.source.toString());

            Event readEvent = new Event();
            readEvent.source = event.source;
            readEvent.type = EventType.WRITE;

            selector.addEvent(readEvent);
        }
    }
}
