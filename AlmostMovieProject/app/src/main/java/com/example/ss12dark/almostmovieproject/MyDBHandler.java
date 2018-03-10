package com.example.ss12dark.almostmovieproject;

/**
 * Created by Ss12Dark on 3/9/2018.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;


//this class handles the all database tasks and extends the SQLiteOpenHelper (a build class)
//in this class we must to implement the methods "onCreate" and "onUpgrade" from the SQLiteOpenHelper class
public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieLab";
    public static final String TABLE_MOVIES = "Movies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOVIENAME = "movieName";
    public static final String COLUMN_MOVIEDES= "movieDescription";
    public static final String COLUMN_MOVIEURL = "movieURL";


    //We need to pass database information along to superclass because the super class doesn't have any default constructor
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //in the first time that we run this app we want to create our table in order to store data
    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_MOVIES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MOVIENAME + " TEXT " +  COLUMN_MOVIEDES + " TEXT " + COLUMN_MOVIEURL + " TEXT " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }


    public void addMovie(Movie movie){
        ContentValues values = new ContentValues();

        values.put(COLUMN_MOVIENAME, movie.getSubject());
        values.put(COLUMN_MOVIEDES, movie.getBody());
        values.put(COLUMN_MOVIEURL, movie.getUrl());
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.insert(TABLE_MOVIES, "", values);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }


    public void deleteProduct(String movieName){
        SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_MOVIES + " WHERE " + COLUMN_MOVIENAME + "=\"" + movieName + "\";");
    }


    public List databaseToList(){
        String dbString = "";
        List<String> names = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIES ;


        Cursor recordSet = db.rawQuery(query, null,null);


        recordSet.moveToFirst();

       while (!recordSet.isAfterLast()) {


            if (recordSet.getString(recordSet.getColumnIndex("MovieName")) != null) {
                dbString = recordSet.getString(recordSet.getColumnIndex("MovieName"));
                names.add(dbString);
            }

            recordSet.moveToNext();
        }
        db.close();
        return names;
    }

}

