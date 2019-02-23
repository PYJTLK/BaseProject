package com.example.baseproject;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Administrator on 2019/2/22.
 */

@Database(version = AppDatabase.VERSON,name = AppDatabase.NAME)
public class AppDatabase {
    //数据库名字
    public final static String NAME = "AppDatabase";
    //数据库版本号
    public final static int VERSON = 1;
}
