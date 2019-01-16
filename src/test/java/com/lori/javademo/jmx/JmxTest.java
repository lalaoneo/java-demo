package com.lori.javademo.jmx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.*;
import javax.management.remote.*;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmxTest {

    @Test
    public void testJmxForClient() throws Exception{
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName objectName = new ObjectName("jmxBean:name=hello");

        Hello hello = new Hello();

        server.registerMBean(hello,objectName);

        LocateRegistry.createRegistry(9999);

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");

        JMXConnectorServer connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url,null,server);

        System.out.println("begin rmi start");

        connectorServer.start();

        System.out.println("rmi started");


        JMXServiceURL clientUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");

        JMXConnector connector = JMXConnectorFactory.connect(clientUrl);

        MBeanServerConnection connection = connector.getMBeanServerConnection();

        ObjectName clientObjectName = new ObjectName("jmxBean:name=hello");

        String[] domains = connection.getDomains();

        for (int i=0; i<domains.length; i++){
            System.out.println("domain[" + i + "]=" + domains[i]);
        }

        System.out.println("MBean count = " + connection.getMBeanCount());

        /**
         * 设置指定Mbean的特定属性值
         * 这里的setAttribute、getAttribute操作只能针对bean的属性
         * 例如对getName或者setName进行操作，只能使用Name，需要去除方法的前缀
         */
        connection.setAttribute(clientObjectName,new Attribute("Name","杭州"));
        connection.setAttribute(clientObjectName,new Attribute("Age","1990"));

        String name = (String) connection.getAttribute(clientObjectName,"Name");
        String age = (String) connection.getAttribute(clientObjectName,"Age");
        System.out.println("age=" + age + ";name=" + name);

        HelloMBean proxy = MBeanServerInvocationHandler.newProxyInstance(connection,clientObjectName,HelloMBean.class,false);

        proxy.helloWorld();

        proxy.helloWorld("i am first jmx");

        /**
         * invoke调用bean的方法，只针对非设置属性的方法
         * 例如invoke不能对setName方法进行调用
         */
        connection.invoke(clientObjectName,"helloWorld",new String[]{"I'll connect to JMX Server via client2"},new String[]{"java.lang.String"});

        connection.invoke(clientObjectName,"helloWorld",null,null);

    }

    @Test
    public void testJmxForNotification() throws Exception{

        /**
         *  MBean之间的通信是必不可少的，Notification就起到了在MBean之间沟通桥梁的作用。JMX 的通知由四部分组成：
         *  1、Notification这个相当于一个信息包，封装了需要传递的信息
         *  2、Notification broadcaster这个相当于一个广播器，把消息广播出
         *  3、Notification listener 这是一个监听器，用于监听广播出来的通知信息
         *  4、Notification filiter 这个一个过滤器，过滤掉不需要的通知。这个一般很少使用
         */

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        Hello hello = new Hello();

        Jack jack = new Jack();

        server.registerMBean(jack,new ObjectName("jmxBean:name=Jack"));

        jack.addNotificationListener(new HelloListener(),null,hello);

        // 需要通过jConsole来操作的
//        Thread.sleep(60*60*1000);
    }
}
