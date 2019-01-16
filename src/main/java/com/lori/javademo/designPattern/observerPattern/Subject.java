package com.lori.javademo.designPattern.observerPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    //观察者
    List<Observer> observers = new ArrayList<>();

    public void register(Observer observer){
        observers.add(observer);
    }

    public void deleteObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(String newState){
        for (Observer observer : observers){
            observer.updateState(newState);
        }
    }
}
