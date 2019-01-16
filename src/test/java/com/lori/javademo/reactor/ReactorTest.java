package com.lori.javademo.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactorTest {

    @Test
    public void testReactor() {

        Server server = new Server(8888,10);
        server.start();
    }
}
