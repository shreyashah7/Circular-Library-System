package com.example.backg;


import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Membership extends Activity {
	private SQLiteDatabase db;
	File sdCrd=Environment.getExternalStorageDirectory();
	SharedPreferences shared_preferences;
	  SharedPreferences.Editor shared_preferences_editor;
	  String test_string = "";
	
	private static final String DATABASE_NAME = "membership.db";

	
	private static final String TABLE_CONTACTS = "membership";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.membershipxml);
		 Button diamond = (Button) findViewById(R.id.diamond);
		 Button gold = (Button) findViewById(R.id.gold);
		 Button silver = (Button) findViewById(R.id.silver);
		 SharedPreferences shared_preferences;
		  SharedPreferences.Editor shared_preferences_editor;
		  String test_string = "";

		 
		 shared_preferences = getSharedPreferences("shared_preferences_test",
			        MODE_PRIVATE);
		final String t1=shared_preferences.getString("email", "Default");
		 diamond.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					
			/*	db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME, null,SQLiteDatabase.OPEN_READWRITE);	
					try{
					ContentValues values = new ContentValues();
					values.put("email", t1.toString());
					values.put("membership_type", "diamond"); 
					db.insertOrThrow(TABLE_CONTACTS, null, values);*/
					String s= "diamond";
					SharedPreferences shared_preferences;
					  SharedPreferences.Editor shared_preferences_editor;
					  shared_preferences = getSharedPreferences("shared_preferences_test",
						        MODE_PRIVATE);
					  shared_preferences_editor =shared_preferences.edit();
					  shared_preferences_editor.putString("m", s);
					  shared_preferences_editor.commit();

					Intent i1= new Intent(Membership.this,Bank.class);
					
					startActivity(i1);
					 
				/*	db.close(); 
					}
					catch (SQLException e) {
						Toast.makeText(Membership.this, "You already have membership",
								Toast.LENGTH_LONG).show();
					}*/
					// Closing database connection*/
					
				}
		 
	});
		 
		 gold.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME, null,SQLiteDatabase.OPEN_READWRITE);	
					try{
					ContentValues values = new ContentValues();
					values.put("email", t1.toString());
					values.put("membership_type", "gold"); 
					db.insertOrThrow(TABLE_CONTACTS, null, values);
					
					String s= "gold";
					SharedPreferences shared_preferences;
					  SharedPreferences.Editor shared_preferences_editor;
					  shared_preferences = getSharedPreferences("shared_preferences_test",
						        MODE_PRIVATE);
					  shared_preferences_editor =shared_preferences.edit();
					  shared_preferences_editor.putString("m", s);
					  shared_preferences_editor.commit();
					
					
				Intent i2= new Intent(Membership.this,Bank.class);
					startActivity(i2);
					
					db.close(); 
					}
					catch (SQLException e) {
						Toast.makeText(Membership.this, "You already have membership",
								Toast.LENGTH_LONG).show();
					}
					
				}
		 
	});
		 silver.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME, null,SQLiteDatabase.OPEN_READWRITE);	
					try{
					ContentValues values = new ContentValues();
					values.put("email", t1.toString());
					values.put("membership_type", "silver"); 
					db.insertOrThrow(TABLE_CONTACTS, null, values);
					String s= "silver";
					SharedPreferences shared_preferences;
					  SharedPreferences.Editor shared_preferences_editor;
					  shared_preferences = getSharedPreferences("shared_preferences_test",
						        MODE_PRIVATE);
					  shared_preferences_editor =shared_preferences.edit();
					  shared_preferences_editor.putString("m", s);
					  shared_preferences_editor.commit();
					
					Intent i3= new Intent(Membership.this,Bank.class);
					startActivity(i3);
					
					db.close(); 
					}
					catch (SQLException e) {
						Toast.makeText(Membership.this, "You already have membership",
								Toast.LENGTH_LONG).show();
					}
					
				}
		 
	});


}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}

}
