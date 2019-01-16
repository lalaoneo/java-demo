package com.lori.javademo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.atomic.AtomicInteger;

public class MyJob implements Job {

    private static AtomicInteger integer = new AtomicInteger(1);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("started at the "+integer.getAndIncrement()+" times");
    }
}
