package com.example.srikar.s_b35_asynctaskfirsteg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sunilkumar on 11-07-2017.
 */

public class MyDataBase {
    Contacts c;
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;

    public MyDataBase(Context context){

        myHelper = new MyHelper(context,"techpalle.db",null,1);

    }
    public void open(){
        sqLiteDatabase = myHelper.getWritableDatabase();
    }

    //---close SQLite DB---
    public void close() {

        sqLiteDatabase.close();
    }

    //---Delete All Data from table in SQLite DB---
    public void deleteAll() {

        sqLiteDatabase.delete("a", null, null);
    }

    public Cursor getData(){
        Cursor c = null;
        c = sqLiteDatabase.rawQuery("select * from a",null);
        return c;

    }


    public void insert(Contacts c){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",c.getSno());
        contentValues.put("name",c.getName());
        contentValues.put("email",c.getEmail());
        contentValues.put("mobile",c.getMobile());
        contentValues.put("image",c.getImageUrl());

        sqLiteDatabase.insert("a",null,contentValues);

    }

    public class MyHelper extends SQLiteOpenHelper{

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table if not exists a(id text,name text,email text,mobile text,image text);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


}
