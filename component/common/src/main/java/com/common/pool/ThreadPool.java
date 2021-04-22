package com.common.pool;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ThreadPool {
    //keepAliveTime 超过core实惠保留那么长，没有新任务则销毁

    //一定要注意控制任务的数量
    //工作线程的创建数量几乎没有限制
    //如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。
    //Executors.newCachedThreadPool();
    public final ExecutorService unLimitedExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    //指定工作线程数量的线程池
    //达到线程池初始的最大数，则将提交的任务存入到池队列中
    //线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
    // Executors.newFixedThreadPool();
    int coreNum=Runtime.getRuntime().availableProcessors();
    public final ExecutorService limitedNumExecutor = new ThreadPoolExecutor(coreNum * 2, coreNum*2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    //只创建唯一的工作者线程来执行任务
    //保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
    //如果这个线程异常结束，会有另一个取代它，保证顺序执行。单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的
    public final ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
    //定长的线程池
    //支持定时的以及周期性的任务执行
    //一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行
    //同时放入多个线程时，每个线程都会按照自己的调度来执行，但是当其中一个线程被阻塞时，其它的线程都会受到影响被阻塞，不过依然都会按照自身调度来执行，但是会存在阻塞延迟。
    public final ExecutorService singleScheduleExecutor = Executors.newSingleThreadScheduledExecutor();

}
