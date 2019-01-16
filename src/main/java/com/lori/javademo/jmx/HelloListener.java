package com.lori.javademo.jmx;

import javax.management.Notification;
import javax.management.NotificationListener;

public class HelloListener implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (handback instanceof Hello){
            Hello hello = (Hello) handback;
            hello.println(notification.getMessage());
        }
    }
}
