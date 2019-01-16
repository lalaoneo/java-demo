package com.lori.javademo.reactor;

public class AcceptEventHandler extends EventHandler {

    private DeMultiplexer selector;

    public AcceptEventHandler(DeMultiplexer selector) {
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {
        if (event.type == EventType.ACCEPT) {

            System.out.println("我们建立连接了，骚年"+event.source.toString());

            Event readEvent = new Event();
            readEvent.source = event.source;
            readEvent.type = EventType.READ;

            selector.addEvent(readEvent);
        }
    }
}
