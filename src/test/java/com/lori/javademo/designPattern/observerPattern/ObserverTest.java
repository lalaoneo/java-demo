package com.lori.javademo.designPattern.observerPattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObserverTest {

    @Test
    public void testObserver() {

        ConcreteSubject subject = new ConcreteSubject();

        Observer observer = new ConcreteObserver("observer");

        Observer observer2 = new ConcreteObserver("observer2");

        Observer observer3 = new ConcreteObserver("observer3");
        subject.register(observer);
        subject.register(observer2);
        subject.register(observer3);

        subject.change("lori-test");
    }
}
