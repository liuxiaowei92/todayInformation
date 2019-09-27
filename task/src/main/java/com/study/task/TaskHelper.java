package com.study.task;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public class TaskHelper {

    public static void submitTask(ITaskBackground iTaskBackground,ITaskCallback iTaskCallback){
        AsyncTaskInstance instance = AsyncTaskInstance.getInstance(iTaskBackground, iTaskCallback);
        //构建线程池管理器
        TaskScheduler.getInstance().submit(instance);
    }
}
