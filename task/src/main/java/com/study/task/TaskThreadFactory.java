package com.study.task;

import java.util.concurrent.ThreadFactory;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public class TaskThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "task_thread_pool");
    }
}
