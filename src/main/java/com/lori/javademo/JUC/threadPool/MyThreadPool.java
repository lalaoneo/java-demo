package com.lori.javademo.JUC.threadPool;

import java.util.LinkedList;
import java.util.List;

/**
 * 线程池类，线程管理器：创建线程，执行任务，销毁线程，获取线程基本信息
 */
public final class MyThreadPool {
    /**
     * 线程池中默认线程的个数为5
     */
    private static int worker_num = 5;

    /**
     * 工作线程
     */
    private WorkThread[] workThreads;

    /**
     * 未处理的任务
     */
    private static volatile int finished_task = 0;

    /**
     * 任务队列，作为一个缓冲,List线程不安全
     */
    private List<Runnable> taskQuene = new LinkedList<>();

    private static MyThreadPool myThreadPool;

    /**
     * 创建具有默认线程个数的线程池
     */
    private MyThreadPool(){
        this(5);
    }

    /**
     * 创建线程池,worker_num为线程池中工作线程的个数
     */
    private MyThreadPool(int worker_num){
        MyThreadPool.worker_num = worker_num;
        workThreads = new WorkThread[worker_num];
        for (int i =0 ;i < worker_num; i ++){
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }

    /**
     * 单态模式，获得一个默认线程个数的线程池
     */
    public static MyThreadPool getMyThreadPool(){
        return getMyThreadPool(MyThreadPool.worker_num);
    }

    /**
     * 单态模式，获得一个指定线程个数的线程池,worker_num(>0)为线程池中工作线程的个数
     * worker_num<=0创建默认的工作线程个数
     */
    public static MyThreadPool getMyThreadPool(int worker_num){
        if (worker_num <= 0){
            worker_num = MyThreadPool.worker_num;
        }
        if (myThreadPool == null){
            myThreadPool = new MyThreadPool(worker_num);
        }
        return myThreadPool;
    }

    /**
     * 执行任务,其实只是把任务加入任务队列，什么时候执行有线程池管理器觉定
     */
    public void execute(Runnable task){
        synchronized (taskQuene){
            taskQuene.add(task);
            taskQuene.notify();
        }
    }

    /**
     * 批量执行任务,其实只是把任务加入任务队列，什么时候执行有线程池管理器觉定
     */
    public void execute(Runnable[] task){
        synchronized (taskQuene){
            for (Runnable runnable : task){
                taskQuene.add(runnable);
            }
            taskQuene.notify();
        }
    }

    /**
     * 批量执行任务,其实只是把任务加入任务队列，什么时候执行有线程池管理器觉定
     */
    public void execute(List<Runnable> task){
        Runnable[] runnables = task.toArray(new Runnable[task.size()]);
        this.execute(runnables);
    }

    /**
     * 销毁线程池,该方法保证在所有任务都完成的情况下才销毁所有线程，否则等待任务完成才销毁
     */
    public void destory(){
        while (!taskQuene.isEmpty()){
            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /**
         * 工作线程停止工作，且置为null
         */
        for (int i = 0;i<worker_num;i++){
            workThreads[i].stopWorker();
            workThreads[i]= null;
        }
        myThreadPool = null;
        taskQuene.clear();
    }

    /**
     * 返回工作线程的个数
     */
    public int getFinishedTaskNumber(){
        return finished_task;
    }

    /**
     * 返回任务队列的长度，即还没处理的任务个数
     */
    public int getWaitTaskNumber(){
        return taskQuene.size();
    }

    /**
     * 覆盖toString方法，返回线程池信息：工作线程个数和已完成任务个数
     * @return
     */
    @Override
    public String toString() {
        return "WorkThread number:" + worker_num + "  finished task number:"
                + finished_task + "  wait task number:" + getWaitTaskNumber();
    }

    /**
     * 内部类，工作线程
     */
    private class WorkThread extends Thread{
        /**
         * 该工作线程是否有效，用于结束该工作线程
         */
        private boolean isRunning = true;

        /**
         * 关键所在啊，如果任务队列不空，则取出任务执行，若任务队列空，则等待
         */
        @Override
        public void run(){
            while (isRunning){
                synchronized (taskQuene){
                    // 队列为空
                    while (isRunning && taskQuene.isEmpty()){
                        try {
                            taskQuene.wait(20);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if (!taskQuene.isEmpty()){
                        Runnable r = taskQuene.remove(0);
                        if (r != null){
//                            r.run();
                            //不能使用run,run是在当前线程执行,否则当阻塞子线程时会阻塞掉主线程
                            new Thread(r).start();
                        }
                        finished_task ++;
                    }
                }
            }
        }

        /**
         * 停止工作，让该线程自然执行完run方法，自然结束
         */
        public void stopWorker(){
            isRunning = false;
        }
    }
}
