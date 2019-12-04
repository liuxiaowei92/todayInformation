package com.study.todayinformation.main.beijing;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * @authour lxw
 * @function 内容提供者获取数据 进程间通信
 * @date 2019/11/20
 */
public class MainProcessDataProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    //可以提供访问数据库的能力
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    //数据库的插入
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String processDec = ProcessDataTest.getInstance().getProcessDec();
        return Uri.parse(processDec);
    }
    //数据库的删除
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
    //数据库的修改
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
