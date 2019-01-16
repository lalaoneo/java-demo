## quartz 运行机制

- 创建调度工厂:StdSchedulerFactory

- 通过调度工厂创建调度实例:Scheduler
    - 构建线程池执行线程QuartzSchedulerThread
    - 计算下一次的执行时间,通过Object.wait(time)控制
    
- 通过JobBuilder创建JobDetail

- 通过TriggerBuilder创建Trigger

- scheduler.scheduleJob注册任务和定时器
    - 通知监听器
    - 通知线程池的线程开始执行任务
    
- scheduler.start()启动调度器
    - 通知监听器
