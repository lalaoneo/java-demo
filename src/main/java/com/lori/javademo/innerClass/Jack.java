package com.lori.javademo.innerClass;

public class Jack {

    private String name = "Jack";

    private Integer age = 30;

    /**
     * 内部类可以很好的实现隐藏
     * 一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
     */
    private class JackWife extends Person implements Hello{

        @Override
        public void hi() {
            System.out.println("hi i am jack's wife");
            /**
             * 2．内部类拥有外围类的所有元素的访问权限
             */
            System.out.println("my husband's name is "+name);
        }

        public void eat(String string){
            super.eat(string);
        }
    }

    private class JackSon extends Animal{
        public void work(){
            super.work();
        }
    }

    public Hello getJackWife(){
        return new JackWife();
    }

    public void eat(String string){
        new JackWife().eat(string);
    }

    public void work(){
        new JackSon().work();
    }
}
