package com.example.kashish.valetparking;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;


public class MyContentProvider extends ContentProvider {

    SQLiteDatabase database;
    DataBaseHelper helper;
    @Override
    public boolean onCreate() {
        helper=new DataBaseHelper(getContext());
        database=helper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName=uri.getLastPathSegment();
        long l=database.insert(tableName,null,values);
        Uri u=Uri.parse("anyuri://anything/"+l);
        return u;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context context) {
            super(context, utils.dbName, null, utils.dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(utils.create_table_query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists "+utils.tableName);
            onCreate(db);
        }


    }


}
