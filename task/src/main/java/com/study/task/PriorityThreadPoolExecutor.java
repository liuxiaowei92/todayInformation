package com.study.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @authour lxw
 * @function 线程池
 * @date 2019/9/26
 */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {
    /**
     * @param corePoolSize 核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 多余线程存活时间
     * @param unit 存活单位
     * @param workQueue 任务队列
     * @param threadFactory 线程工厂
     *  RejectedExecutionHandler 拒绝任务
     */
    public PriorityThreadPoolExecutor(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue,
                                      ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);

    }
}
