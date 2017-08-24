package com.example.backg;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Return_book  extends Activity {
	private SQLiteDatabase db;
	
	File sdCrd=Environment.getExternalStorageDirectory();
	private static final String DATABASE_NAME1 = "user_issue.db";
	private static final String TABLE_CONTACTS1 = "user_issue";
	private static final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.return_book);
		SharedPreferences shared_preferences;
		  SharedPreferences.Editor shared_preferences_editor;
		shared_preferences = getSharedPreferences("shared_preferences_test",MODE_PRIVATE);
		final String t1=shared_preferences.getString("email", "Default");
		 Calendar c = Calendar.getInstance();
	        System.out.println("Current time => "+c.getTime());

	        
	        
	       final String f = df.format(c.getTime());
		final ListView l = (ListView) findViewById(R.id.listView1);
		ArrayList<String> dataList1 = new ArrayList<String>();
		db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME1, null,SQLiteDatabase.OPEN_READONLY);
		Cursor m = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS1 + " where  user= '" + t1 + "'" , null);
		if (m.moveToFirst()) {
			do {
			//Contact contact = new Contact();
			
			//contact.setstatus(m.getString(2));
			// Adding contact to list
			dataList1.add(m.getString(2));
			
			} while (m.moveToNext());
		}
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_list_item_1, android.R.id.text1,dataList1);
       l.setAdapter(adapter);
       l.setOnItemClickListener(new OnItemClickListener() {

           public void onItemClick(AdapterView<?> arg0, View arg1,
                   int pos, long id) {
               // TODO Auto-generated method stub
        	   String selectedFromList =(String) (l.getItemAtPosition(pos));
        	   
        	   db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME1, null,SQLiteDatabase.OPEN_READONLY);
       		Cursor m = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS1 + " where book ='"+ selectedFromList + "' and user= '" + t1 + "'" , null);
       		
       		
       			if (m.moveToFirst()) {
           			do {
           				String dateStop = m.getString(5);
           				long now = System.currentTimeMillis();

           				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");  


           				Date d1 = null;
           				Date d2 = null;
           				try {
           				     d1 = new Date (now);
           				     d2 = (Date) format.parse(dateStop);
           				} 
           				 catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}   
           				int difference =(int) (d1.getTime() - d2.getTime())/(1000*60*60*24);
           				if(difference > 0 )
           				{
					Toast.makeText(Return_book.this,"Book's return Request sent succesfully" ,
							Toast.LENGTH_LONG).show();
           				}
           				else
           				{
           					Toast.makeText(Return_book.this,"Return Date was " + m.getString(5) + "..so Penalty is Rs" + difference ,
        							Toast.LENGTH_LONG).show();
           				}
		System.out.println("time" + difference);
           		} while (m.moveToNext());
       			}
       		
       			
       			
       		
           }
           
			
       });
       
	}
	public static Calendar createFromString(String date) throws java.text.ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(df.parse(date));
        return c;  
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
