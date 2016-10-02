package com.example.mahitha.assignment_3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by Mahitha on 02/10/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";

    private static final String TABLE_2_NAME = "blogs";
    private static final String COLUMN_2_ID = "id";
    private static final String COLUMN_2_ANAME = "aname";
    private static final String COLUMN_2_TOPIC = "topic";
    private static final String COLUMN_2_BODY = "body";

    SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , "
            +"name text not null, email text not null, uname text not null, pass text not null)";

    private static final String TABLE_2_CREATE = "create table blogs (id integer primary key not null , "
            +"aname text not null, topic text not null, body text not null, pass text not null)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_2_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_UNAME, c.getUsername());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASS, c.getPassword());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void deleteContact(Contact contact){
        db = this.getReadableDatabase();
        String u_name = contact.getUsername();
        String query = "delete * from contacts where uname = "+u_name;
        db.execSQL(query);
        db.close();
        /*if(COLUMN_UNAME.equals(contact.getUsername())){
            int id  = contact.getId();
            db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_ID
                    + " = " + id, null);
        }
        */
    }

    public void updateContact(){

    }

    public String searchPass(String uname){
        db = this.getReadableDatabase();
        String query = "select uname, pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String u, p;
        p = "Not Found";

        if(cursor.moveToFirst())
        {
            do{
                u = cursor.getString(0);

                if(u.equals(uname)){
                    p = cursor.getString(1);
                    break;
                }
            } while(cursor.moveToNext());
        }

        return p;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
