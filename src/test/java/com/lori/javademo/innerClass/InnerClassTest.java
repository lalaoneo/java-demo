package com.lori.javademo.innerClass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InnerClassTest {

    @Test
    public void testInnerClass(){
        /**
         * 1.内部类可以很好的实现隐藏
         */
        Jack jack = new Jack();
        jack.getJackWife().hi();

        /**
         * 3.可是实现多重继承
         */
        jack.eat("apple");
        jack.work();


        /**
         * 可以避免修改接口而实现同一个类中两种同名方法的调用
         */
        ImplAndExtends implAndExtends = new ImplAndExtends();

        implAndExtends.hi();

        implAndExtends.innerHi();
    }
}
