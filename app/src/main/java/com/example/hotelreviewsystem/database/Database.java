package com.example.hotelreviewsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="HotelAppDB";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_USER="Users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT);";

    public static final String TABLE_HOTEL="Hotels";
    public static final String COLUMN_HOTEL_ID="id";
    public static final String COLUMN_HOTEL_NAME="Hotel_Name";
    public static final String COLUMN_HOTEL_CITY="Hotel_City";
    public static final String COLUMN_HOTEL_LOCATION="Hotel_Location";
    public static final String COLUMN_RATING="Average_Rating";

    private static final String CREATE_TABLE_HOTELS = "CREATE TABLE " + TABLE_HOTEL + " (" +
            COLUMN_HOTEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_HOTEL_NAME + " TEXT, " +
            COLUMN_HOTEL_CITY + " TEXT, " +
            COLUMN_HOTEL_LOCATION + " TEXT, " +
            COLUMN_RATING + " NUMBER DEFAULT 5);";

    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_HOTELS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_HOTEL);
        onCreate(db);
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean checkUser(String username){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+ TABLE_USER+" WHERE "+COLUMN_USERNAME+"=?";
        Cursor cursor=db.rawQuery(query,new String[]{username});
        boolean userExists=cursor.getCount()>0;
        cursor.close();
        db.close();
        return userExists;
    }

    public boolean loginCheck(String username,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+ TABLE_USER+" WHERE "+COLUMN_USERNAME+"=? AND "+COLUMN_PASSWORD+ "=?";
        Cursor cursor=db.rawQuery(query,new String[]{username,password});
        boolean userExists=cursor.getCount()>0;
        cursor.close();
        db.close();
        return userExists;
    }

    public boolean insertHotel(String hotel_name,String city,String location){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Hotel_name",hotel_name);
        contentValues.put("Hotel_City",city);
        contentValues.put("Hotel_Location",location);
        long result=db.insert("Hotels",null,contentValues);
        return result!=-1;
    }
}
