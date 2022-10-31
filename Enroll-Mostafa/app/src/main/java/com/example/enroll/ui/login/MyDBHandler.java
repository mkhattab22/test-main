package com.example.enroll.ui.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.*;
import java.util.*;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final String COURSE_TABLE_NAME = "courses";
    private static final String COLUMN_COURSE_CODE = "course_code";
    private static final String COLUMN_COURSE_NAME = "course_name";

    private static final String ACCOUNT_TABLE_NAME = "accounts";
    private static final String COLUMN_ACCOUNT_TYPE = "account_type";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAME = "name";

    private static final String DATABASE_NAME = "enroll.db";
    private static final int DATABASE_VERSION = 1;

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override


    public void onCreate(SQLiteDatabase db) {
        String create_course_table_cmd = "CREATE TABLE " + COURSE_TABLE_NAME +
                "(" + COLUMN_COURSE_CODE + " TEXT, " +
                COLUMN_COURSE_NAME + " TEXT " + ")";

        String create_account_table_cmd = "CREATE TABLE " + ACCOUNT_TABLE_NAME +
                "(" + COLUMN_ACCOUNT_TYPE + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT " + ")";

        db.execSQL(create_course_table_cmd);
        db.execSQL(create_account_table_cmd);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);
        onCreate(db);
    }

    public Cursor getCourseData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSE_TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public boolean createCourse(String course_code, String course_name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_COURSE_CODE, course_code);
        values.put(COLUMN_COURSE_NAME, course_name);

        long result = db.insert(COURSE_TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public boolean editCourse(String original_course_code, String original_course_name,
                           String new_course_code, String new_course_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_COURSE_CODE, new_course_code);
        values.put(COLUMN_COURSE_NAME, new_course_name);

        long result = db.update(COURSE_TABLE_NAME, values, COLUMN_COURSE_CODE+"=?", new String[]{original_course_code});
        if(result == -1){
            return false;
        }
        result = db.update(COURSE_TABLE_NAME, values, COLUMN_COURSE_NAME+"=?", new String[]{original_course_name});
        db.close();

        return result != -1;
    }

    public boolean deleteCourse(String course_code, String course_name){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(COURSE_TABLE_NAME, COLUMN_COURSE_CODE+"=?", new String[]{course_code});
        if(result == -1){
            return false;
        }

        result = db.delete(COURSE_TABLE_NAME, COLUMN_COURSE_NAME+"=?", new String[]{course_name});

        return result != -1;
    }

    public Boolean createAccount(String account_type, String name, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ACCOUNT_TYPE, account_type);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(ACCOUNT_TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean deleteAccount(String username){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(ACCOUNT_TABLE_NAME, COLUMN_USERNAME+"=?", new String[]{username});

        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOUNT_TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=?", new String[]{username
        });

        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkUsernamePassword(String username , String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String search = ("SELECT * FROM " + ACCOUNT_TABLE_NAME +" WHERE " + COLUMN_USERNAME + " = \"" + username + "\"" + " AND " + COLUMN_PASSWORD +
                " = \"" + password + "\"");

        Cursor cursor = db.rawQuery(search, null);

        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public String checkAccountType(String account_type){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOUNT_TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=?", new String[]{account_type});

        cursor.moveToFirst();

        return cursor.getString(1);
    }
    public String checkType(String account_type){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOUNT_TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=?", new String[]{account_type});

        cursor.moveToFirst();

        return cursor.getString(0);
    }


}
