package com.example.fooddel2;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "food";
    private static final String TABLE_NAME = "custs";

    private static final String USER_NAME = "c_name";
    private static final String USER_CON = "c_number";
    private static final String USER_PASSWORD = "c_pass";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+USER_CON+" TEXT PRIMARY KEY, "+USER_NAME+" TEXT, "+USER_PASSWORD+" TEXT);";

    String s = "CREATE TABLE custs(c_number TEXT PRIMARY KEY,c_name TEXT,c_pass TEXT)";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DELETE TABLE IF EXITS " +TABLE_NAME);
//        onCreate(db);
    }

    public void addData(String contact,String name,String pass){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put(USER_CON,contact);
        contentValues.put(USER_NAME,name);
        contentValues.put(USER_PASSWORD,pass);

        database.insert(TABLE_NAME,null,contentValues);

    }

    @SuppressLint("Range")
    public String fetch(String name){
        SQLiteDatabase database = this.getReadableDatabase();
        // Assuming TABLE_NAME and USER_NAME are variables holding table name and column name respectively
        String Q = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_NAME + " = ?";
// Assuming 'name' is the value you're comparing in the WHERE clause
        String[] selectionArgs = { name };
// Execute the query using SQLiteDatabase.rawQuery() or similar method

        @SuppressLint("Recycle") Cursor cursor=database.rawQuery(Q,selectionArgs);
        cursor.moveToNext();

        return cursor.getString(cursor.getColumnIndex(USER_PASSWORD));

    }
    @SuppressLint("Range")
    public String fetchForgot(String conatct){
        SQLiteDatabase database = this.getReadableDatabase();
        // Assuming TABLE_NAME and USER_NAME are variables holding table name and column name respectively
        String Q = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_CON + " = ?";
// Assuming 'name' is the value you're comparing in the WHERE clause
        String[] selectionArgs = { conatct };
// Execute the query using SQLiteDatabase.rawQuery() or similar method

        @SuppressLint("Recycle") Cursor cursor=database.rawQuery(Q,selectionArgs);
        if(cursor.moveToNext())
        {
            return cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
        }
        else{
            return null;
        }

    }

}