package com.lori.javademo.reactor;

import java.util.Date;

public class Source {

    private Date date = new Date();
    private String id = date.toString() + "_" + System.identityHashCode(date);

    @Override
    public String toString() {
        return id;
    }
}
