package com.lori.javademo.designPattern.observerPattern;

public class ConcreteObserver implements Observer {

    private String name;

    public ConcreteObserver(String name){
        this.name = name;
    }

    @Override
    public void updateState(String state) {
        System.out.println(name + " status is : "+state);
    }
}
