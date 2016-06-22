package com.mxy;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * 使用contentProvider的时候 数据库不需要关闭
 * @author mxy
 * 我们在操作该ContentProvider数据的时候，需要根据特定的Uri去操作
 * Uri构造格式如下：content://<AndroidManifest.xml配置的provider的authorities名字>/自定义内容
 */
public class MyProvider extends ContentProvider {

    private static int NOMATCH = -1;
    private static int INSERT = 1;
    private static int QUERY = 2;
    private static int UPDATE = 3;
    private static int DELETE = 4;

    //为了方便我们操作Google提供了UriMatcher，我们可以通过该类去构造我们能够匹配
    //的Uri，当然我们也可以自己去进行匹配，但是那样容易书写错误
    private static UriMatcher matcher = new UriMatcher(NOMATCH);
    static{
        matcher.addURI("com.mxy.MyProvider", "insert", INSERT);
        matcher.addURI("com.mxy.MyProvider", "query", QUERY);
        matcher.addURI("com.mxy.MyProvider", "update", UPDATE);
        matcher.addURI("com.mxy.MyProvider", "delete", DELETE);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i("mxy", "provider  delete" + matcher.match(uri));
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        Log.i("mxy", "provider  getType" + matcher.match(uri));
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i("mxy", "provider  insert" + matcher.match(uri));
        return null;
    }

    @Override
    public boolean onCreate() {
        Log.i("mxy", "provider  onCreate");
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.i("mxy", "provider  query" + matcher.match(uri));
        if(QUERY == matcher.match(uri)){
            PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        }else{
            return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.i("mxy", "provider  update" + matcher.match(uri));
        return 0;
    }

}
