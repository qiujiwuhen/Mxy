package com.mxy;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "lisi");
        values.put("number", "222222");

        long rowid = db.insert("person", null, values);

        if(rowid != -1){
            Log.i("mxy", "添加成功");
        }else{
            Log.i("mxy", "添加失败");
        }

        db.close();

        //读取contentprovider 数据
//        ContentResolver resolver = this.getContentResolver();
//
//        Uri uri = Uri.parse("content://com.mxy.MyPrvider/query");
//        Cursor cursor = resolver.query(uri, null,null ,null, null);
//
//        while(cursor != null && cursor.moveToNext()){
//        	int id = cursor.getInt(cursor.getColumnIndex("id"));
//        	String number = cursor.getString(2);
//        	Log.i("mxy", "id:" + id + "  number:" + number);
//        }

    }


    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.main,menu);
        //return true;
    }

