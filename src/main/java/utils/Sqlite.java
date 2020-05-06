package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
    Author:leia
    Write The Code Change The World    
*/public class Sqlite extends SQLiteOpenHelper {
    public Sqlite(@Nullable Context context) {
        super(context,"result.db" ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(id integer primary key autoincrement,Count varchar(249),Message varchar(249),Player varchar(249) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
