package com.lori.javademo.jmx;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Jack extends NotificationBroadcasterSupport implements JackMBean {

    private int seq = 0;

    @Override
    public void hi() {
        /**
         * 创建一个信息包
         */
        Notification notification = new Notification("jack.hi",this,++seq,System.currentTimeMillis(),"jack");
        sendNotification(notification);
    }
}
