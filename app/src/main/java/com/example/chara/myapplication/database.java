package com.example.chara.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.chara.myapplication.MainActivity.LOG;


/**
 * Created by chara on 12-02-2017.
 */

public class database extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String querystring="CREATE TABLE "+databaselabel.dbs.SONGTABLE+"("+databaselabel.dbs.NAME+" TEXT,"+databaselabel.dbs.AGE+" TEXT,"+databaselabel.dbs.EMAIL+" TEXT,"+databaselabel.dbs.PASWD+" TEXT,"+databaselabel.dbs.LATITUDE+" REAL,"+databaselabel.dbs.LONGITUDE+" REAL);";
    public database(Context context) {
        super(context, databaselabel.dbs.SONGDBASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {  Log.d(LOG,"creating table");

        db.execSQL(querystring);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void putinformation(database dbn,String name,String  age,String email,String passwd)
    {
        SQLiteDatabase db1=dbn.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(databaselabel.dbs.NAME,name);
        cv.put(databaselabel.dbs.AGE,age);
        cv.put(databaselabel.dbs.EMAIL,email);
        cv.put(databaselabel.dbs.PASWD,passwd);
        Log.d(LOG,"putting data done");
        db1.insert(databaselabel.dbs.SONGTABLE,null,cv);
        return;

    }
    public Cursor getinformation(database dbn)
    { Log.d(LOG,"inside cursor activity");
        SQLiteDatabase db1=dbn.getReadableDatabase();
        Log.d(LOG,"inside cursor activity1");
        String[] coloumns={databaselabel.dbs.NAME,databaselabel.dbs.AGE,databaselabel.dbs.EMAIL,databaselabel.dbs.PASWD,databaselabel.dbs.LATITUDE,databaselabel.dbs.LONGITUDE};
        Log.d(LOG,"inside cursor activity2");
        Cursor cr=db1.query(databaselabel.dbs.SONGTABLE,coloumns,null,null,null,null,null);
        Log.d(LOG,"inside cursor activity3");
        return cr;
    }
    public void updateinformation(database dbn,double latitude,double longitude)
    {
        Log.d(LOG,"updating location");
        SQLiteDatabase db1=dbn.getWritableDatabase();
        ContentValues cvv=new ContentValues();
        cvv.put(databaselabel.dbs.LATITUDE,latitude);
        cvv.put(databaselabel.dbs.LONGITUDE,longitude);
        db1.update(databaselabel.dbs.SONGTABLE,cvv,null,null);
    }
}
