package com.lori.javademo.innerClass;

public class ImplAndExtends extends Person {

    public void hi(){
        System.out.println(" ni hao ");
        super.hi();
    }

    private class innerClass implements Hello{

        @Override
        public void hi() {
            System.out.println(" i am inner class");
        }
    }

    public void innerHi(){
        new innerClass().hi();
    }
}
