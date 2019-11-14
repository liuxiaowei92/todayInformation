package com.study.todayinformation.base.crash;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;

/**
 * @authour lxw
 * @function 异常保护管理类
 * @date 2019/11/14
 */
public class CrashProtectManager {
    private static CrashProtectManager mInstance;
    private static Context mContext;

    private CrashProtectManager() {

    }

    public static CrashProtectManager getInstance(Context context) {
        if (mInstance == null) {
            //避免内存溢出
            mContext = context.getApplicationContext();
            mInstance = new CrashProtectManager();
        }
        return mInstance;
    }

    public void init() {
        //crash 防护
        //程序不崩溃 主线程卡死？ 子线程不会卡死
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                //存储错误日志 上传服务器
                handleFileException(e);
                Log.e("uncaughtException", "是不是发生奔溃了");
                //判断是不是主线程
                if (t == Looper.getMainLooper().getThread()) {
                    handleMainThread(e);
                }
            }
        });
    }

    private void handleMainThread(Throwable e) {
        while (true) {//加上死循环 trycatch后 程序不会异常闪退、卡死
            try {
                Looper.loop(); //本身就是死循环  只有奔溃后跳出(参考底层ActivityThread类main方法)
            } catch (Throwable e1) {
                handleFileException(e1);
                Log.e("handleMainThread", "是不是发生奔溃了");
            }
        }
    }

    //日志文件系统
    private void handleFileException(Throwable e) {
        //通过ThRowable 生成字符串
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printWriter.close();
        String result = writer.toString();
        //定义文件名
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = dateFormat.format(new Date());
        String fileName = "crash-" + time + ".txt";
        try {
            //判断有没有SD卡
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //sd卡cache目录
                File cacheDir = mContext.getCacheDir();
                if (!cacheDir.exists()) {
                    cacheDir.mkdirs();//创建文件目录
                }
                File cacheFile = new File(cacheDir.getAbsolutePath(), fileName);
                if (!cacheFile.exists()) {
                    cacheFile.createNewFile();
                }
                //把字符串写入到文件
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                outputStream.write(result.getBytes());
                outputStream.close();
                //把文件上传到服务器
                //获取到文件夹下所有文件
                //File[] files = cacheDir.listFiles();

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

