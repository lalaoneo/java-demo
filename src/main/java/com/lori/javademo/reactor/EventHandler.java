package com.lori.javademo.reactor;

public abstract class EventHandler {

    Source source;

    public abstract void handle(Event event);

    public Source getSource() {
        return source;
    }
}
