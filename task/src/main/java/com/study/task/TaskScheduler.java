package com.study.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.study.task.tools.ThreadUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public class TaskScheduler {
    private final PriorityThreadPoolExecutor executor;

    interface ITaskSchedulerType {
        int SUMIT_TASK = 0;
    }

    private static TaskScheduler instance;
    private final Handler handler;
    private int corePoolSize = ThreadUtil.CPU_NUM + 1;
    private int maximumPoolSize = corePoolSize * 3;
    private int keepAliveTime = 1;

    private TaskScheduler() {
        //用于消息调度的线程
        HandlerThread handlerThread = new HandlerThread("task-thread");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            //运行在子线程
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case ITaskSchedulerType.SUMIT_TASK:
                        doSubmitTask((AsyncTaskInstance) msg.obj);
                        break;
                    default:
                }
                return false;
            }
        });
        //创建一个线程池
        //创建队列
        BlockingQueue<Runnable> poolQueue=new LinkedBlockingDeque<>();
        this.executor = new PriorityThreadPoolExecutor(corePoolSize
                , maximumPoolSize
                , keepAliveTime
                , TimeUnit.MINUTES
        ,poolQueue
        ,new TaskThreadFactory());
    }

    private void doSubmitTask(AsyncTaskInstance asyncTaskInstance) {
        executor.submit(asyncTaskInstance);
    }

    public static TaskScheduler getInstance() {
        if (instance == null) {
            instance = new TaskScheduler();
        }
        return instance;
    }

    //提交任务
    public void submit(AsyncTaskInstance instance) {
        handler.sendMessage(handler.obtainMessage(ITaskSchedulerType.SUMIT_TASK, instance));
    }
}
