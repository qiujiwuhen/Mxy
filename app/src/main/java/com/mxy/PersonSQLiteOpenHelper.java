package com.mxy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {


    public PersonSQLiteOpenHelper(Context context) {
        super(context, "person.db", null, 1);
    }

    /**
     * 数据库第一次被创建的时候调用 可以初始化表和参数等
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表 底层以string格式存储  所以varchar的字符不会起作用
        db.execSQL("create table person (id integer primary key autoincrement,name varchar(20),number varchar(20))");

    }

    /**
     * 数据库版本发生变化的时候调用 可以用于修改数据表结构 数据库参数等
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
