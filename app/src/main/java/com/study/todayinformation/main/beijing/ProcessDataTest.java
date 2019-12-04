package com.study.todayinformation.main.beijing;

/**
 * @authour lxw
 * @function 进程间通信 存储数据类
 * @date 2019/11/20
 */
public class ProcessDataTest {

    private static ProcessDataTest mInstance;

    private ProcessDataTest(){
//        Log.e("ProcessDataTest","PID="+android.os.Process.myPid());
    }

    private String processDec;
    public static ProcessDataTest getInstance(){
        if(mInstance==null){
            synchronized (ProcessDataTest.class){
                if(mInstance==null){
                    mInstance=new ProcessDataTest();
                }
            }
        }
        return mInstance;
    }
    public String getProcessDec() {
        return processDec;
    }

    public ProcessDataTest setProcessDec(String processDec) {
        this.processDec = processDec;
        return this;
    }
}
