package com.example.kashish.valetparking;

import android.net.Uri;

/**
 * Created by Kashish on 7/10/2016.
 */
public class utils {

    public static final int dbVersion=1;
    public static final String dbName="Car.db";
    public static final String tableName="Car";

    public static final String col_car="car";
    public static final String col_name="name";
    public static final String col_phone="phone";
    public static final String col_datetime="datetime";

    public static final String create_table_query="create table "+tableName+"(" +
    col_car+" varchar(20),"+
    col_name+" varchar(30)," +
    col_phone+" varchar(15)," +
    col_datetime+" varchar(30)" +
    ")";

    public static Uri uri=Uri.parse("content://com.example.kashish.valetparking.mycp/"+tableName);
}
