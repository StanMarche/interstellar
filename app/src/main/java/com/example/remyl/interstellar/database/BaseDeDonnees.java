package com.example.remyl.interstellar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper
{

   //Table image
    private static final String TABLE_IMAGE = "image";
    private static final String COL_ID_IMAGE = "idImage";
    private static final String COL_NOM_IMAGE = "txtImage";
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGE + " ("
                                                    + COL_ID_IMAGE + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                                                    + COL_NOM_IMAGE + " TEXT NOT NULL);";

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_IMAGE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.delete(TABLE_IMAGE, null, null);

    }
}
