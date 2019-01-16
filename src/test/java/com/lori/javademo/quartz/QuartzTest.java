package com.lori.javademo.quartz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzTest {

    @Test
    public void testQuartz() throws SchedulerException, InterruptedException {
        //1.创建Scheduler的工厂
        SchedulerFactory factory = new StdSchedulerFactory();
        //2.从工厂中获取调度器实例
        Scheduler scheduler = factory.getScheduler();

        //3.创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withDescription("this is a job demo")
                .withIdentity("myJob","myGroup")
                .build();

        //任务运行的时间，SimpleScheduler类型触发器有效
        //3秒后启动任务
        long time = System.currentTimeMillis()+3*1000L;
        Date startTime = new Date(time);

        //4.创建Trigger
        //使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("myTrigger","myTriggerGroup")
                .startAt(startTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        //5.注册任务和定时器
        scheduler.scheduleJob(jobDetail,trigger);

        //6.启动 调度器
        scheduler.start();

        System.out.println("start new job at the time : "+new Date());

        // 休眠11秒，让定时器执行两次
        Thread.sleep(11000);

        scheduler.shutdown();

        System.out.println(scheduler.getMetaData().getNumberOfJobsExecuted());

        // 判断是否执行了两次
        Assert.assertEquals(Integer.valueOf(2),Integer.valueOf(scheduler.getMetaData().getNumberOfJobsExecuted()));
    }
}
