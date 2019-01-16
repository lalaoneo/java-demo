package com.lori.javademo.jmx;

public class Hello implements HelloMBean {

    private String name = "123";

    private String age = "123";

    public void helloWorld()
    {
        System.out.println("hello world");
    }

    public void helloWorld(String str)
    {
        System.out.println("helloWorld:" + str);
    }

    public String getName()
    {
        System.out.println("get name "+name);
        return name;
    }

    public void setName(String name)
    {
        System.out.println("set name "+ name);
        this.name = name;
    }

    public String getAge()
    {
        System.out.println("get age "+age);
        return age;
    }

    public void setAge(String age)
    {
        System.out.println("set age "+age);
        this.age = age;
    }

    public void println(String string){
        System.out.println("Hello "+string);
    }
}
