package com.example.backg;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;



public class DBAdapter 
{
	private static final String DATABASE_NAME = "login.db";
	private static final String DATABASE_TABLE = "login";
	
	public static final String KEY_USERNAME = "email";
	public static final String KEY_PASSWORD = "password";
	
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_CITY = "city";
	public static final String KEY_PHNO = "phone";
	SQLiteDatabase mDb;
	Context mCtx;
	DBHelper mDbHelper;
	private SQLiteDatabase db;
	File sdCrd=Environment.getExternalStorageDirectory();
	
	public DBAdapter(Context context)
	{
		this.mCtx = context;
	}

	public DBAdapter open() throws SQLException
	{
		mDbHelper = new DBHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		mDbHelper.close();
	}
	
	public long register(String user,String pw,String address,String city,String ph)
	{
		db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME, null,SQLiteDatabase.OPEN_READWRITE);	
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_USERNAME, user);
		initialValues.put(KEY_PASSWORD, pw);
		initialValues.put(KEY_ADDRESS, address);
		initialValues.put(KEY_CITY, city);
		initialValues.put(KEY_PHNO, ph);
		long a=db.insertOrThrow(DATABASE_TABLE, null, initialValues);
		
			db.close();
			return 1;
		

		
	}
	
	public boolean Login(String username, String password) throws SQLException 
    {
		db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME, null,SQLiteDatabase.OPEN_READONLY);	
    	Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE email=? AND password=?", new String[]{username,password});
        if (mCursor != null) {           
            if(mCursor.getCount() > 0)
            {
            	return true;
            }
        }
     return false;
    }
}