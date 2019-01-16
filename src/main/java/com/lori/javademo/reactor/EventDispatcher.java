package com.lori.javademo.reactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventDispatcher {

    Map<EventType, EventHandler> eventHandlerMap = new ConcurrentHashMap<EventType, EventHandler>();

    DeMultiplexer selector;

    EventDispatcher(DeMultiplexer selector) {
        this.selector = selector;
    }

    public void registerEventHandler(EventType eventType, EventHandler eventHandler) {
        eventHandlerMap.put(eventType, eventHandler);

    }

    public void removeEventHandler(EventType eventType) {
        eventHandlerMap.remove(eventType);
    }

    public void handleEvents() {
        dispatch();
    }

    private void dispatch() {
        while (true) {
            List<Event> events = selector.select();

            boolean isClose = false;

            for (Event event : events) {
                if (event.type.equals(EventType.CLOSE)){
                    isClose = true;
                    break;
                }
                EventHandler eventHandler = eventHandlerMap.get(event.type);
                eventHandler.handle(event);
            }

            if (isClose){
                break;
            }
        }
    }
}
