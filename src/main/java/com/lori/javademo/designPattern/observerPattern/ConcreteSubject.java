package com.lori.javademo.designPattern.observerPattern;

public class ConcreteSubject extends Subject {

    public void change(String newState){
        this.notifyObservers(newState);
    }
}
