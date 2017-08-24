package com.example.backg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Selected_item  extends Activity{
	private SQLiteDatabase db,db1,db2;
	File sdCrd=Environment.getExternalStorageDirectory();
	
	
	private static final String DATABASE_NAME = "membership.db";

	private static final String DATABASE_NAME1 = "user_issue.db";
	
	private static final String TABLE_CONTACTS = "membership";
	private static final String TABLE_CONTACTS1 = "user_issue";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selected_item);
		
		final String title= getIntent().getExtras().getString("title");
		final String table= getIntent().getExtras().getString("tablename");
		final String database= getIntent().getExtras().getString("dbname");
		
		SharedPreferences shared_preferences;
		  SharedPreferences.Editor shared_preferences_editor;
		  String test_string = "";
		  Calendar c = Calendar.getInstance();
	        System.out.println("Current time => "+c.getTime());

	        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	       final String formattedDate = df.format(c.getTime());
	        c.add(Calendar.DATE, 30);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	      final  String output = sdf1.format(c.getTime()); 
		 shared_preferences = getSharedPreferences("shared_preferences_test",
			        MODE_PRIVATE);
		 final String t1=shared_preferences.getString("email", "Default");
		
		TextView t= (TextView)findViewById(R.id.textView1);
		final Button btn = (Button) findViewById(R.id.button1);
		final ListView l = (ListView) findViewById(R.id.listView1);
		final Button ret = (Button) findViewById(R.id.button2);
	//TextView title1= (TextView)findViewById(R.id.title);
		ret.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1= new Intent(Selected_item.this,Return_book.class);
				startActivity(i1);
				
				
			}
		});
		
		t.setText("Welcome"+"     " + t1 + "\n your selected items are\n ");
		
		// t.append(title);
		
		String[] statesList = {title +" \t" +"Issue  Date: " + formattedDate + "\n \t \t \t \t \t \t\t"+ " Return Date:"+output};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                  android.R.layout.simple_list_item_1, android.R.id.text1, statesList);
        l.setAdapter(adapter);
        
        adapter.notifyDataSetChanged();
        db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME, null,SQLiteDatabase.OPEN_READONLY);
        Cursor m = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS + " WHERE email = '" + t1 + "'" , null);
		if (m.getCount() >0){
			m.moveToFirst();

		final String s = m.getString(1);
		t.append("\n you have ....." + s);
		db.close();
        l.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1,
                    int pos, long id) {
		db1=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME1, null,SQLiteDatabase.OPEN_READWRITE);
		Cursor m1 = db1.rawQuery("SELECT * FROM " + TABLE_CONTACTS1 + " where user = '" + t1 + "'" , null);
		
		if ( s.equalsIgnoreCase("diamond") && m1.getCount()<3){
		ContentValues values = new ContentValues();
		values.put("user", t1); // Contact Name
		values.put("book", title); // Contact Phone
		values.put("membership", s);
		values.put("issue_date", formattedDate);
		values.put("return_date", output);
		// Inserting Row

		db1.insertOrThrow(TABLE_CONTACTS1, null, values);

		db1.close(); // Closing database connection
		//upadte status
		final String DATABASE_NAME2=database;
		final String TABLE_CONTACTS2=table;
		db2=SQLiteDatabase.openDatabase(sdCrd+File.separator+database, null,SQLiteDatabase.OPEN_READWRITE);
		/*String strFilter = "name=" + title;
		ContentValues args = new ContentValues();
		args.put("status", "0");
		db2.update(table, args, strFilter, null);*/
		db2.execSQL("UPDATE " + table + " SET status='0' WHERE name= '"+ title +"' ");
		db2.close(); 
		Toast.makeText(Selected_item.this, "Book Issued",
				Toast.LENGTH_LONG).show();
            }
		
		else if ( s.equalsIgnoreCase("gold") && m1.getCount()<2){
			ContentValues values = new ContentValues();
			values.put("user", t1); // Contact Name
			values.put("book", title); // Contact Phone
			values.put("membership", s);
			values.put("issue_date", formattedDate);
			values.put("return_date", output);
			// Inserting Row

			db1.insertOrThrow(TABLE_CONTACTS1, null, values);

			db1.close(); // Closing database connection
			//upadte status
			final String DATABASE_NAME2=database;
			final String TABLE_CONTACTS2=table;
			db2=SQLiteDatabase.openDatabase(sdCrd+File.separator+database, null,SQLiteDatabase.OPEN_READWRITE);
			/*String strFilter = "name=" + title;
			ContentValues args = new ContentValues();
			args.put("status", "0");
			db2.update(table, args, strFilter, null);*/
			db2.execSQL("UPDATE " + table + " SET status='0' WHERE name= '"+ title +"' ");
			db2.close(); 
			Toast.makeText(Selected_item.this, "Book Issued",
					Toast.LENGTH_LONG).show();
	            }
			
		else if ( s.equalsIgnoreCase("silver") && m1.getCount()<1){
			ContentValues values = new ContentValues();
			values.put("user", t1); // Contact Name
			values.put("book", title); // Contact Phone
			values.put("membership", s);
			values.put("issue_date", formattedDate);
			values.put("return_date", output);
			// Inserting Row

			db1.insertOrThrow(TABLE_CONTACTS1, null, values);

			db1.close(); // Closing database connection
			//upadte status
			final String DATABASE_NAME2=database;
			final String TABLE_CONTACTS2=table;
			db2=SQLiteDatabase.openDatabase(sdCrd+File.separator+database, null,SQLiteDatabase.OPEN_READWRITE);
			/*String strFilter = "name=" + title;
			ContentValues args = new ContentValues();
			args.put("status", "0");
			db2.update(table, args, strFilter, null);*/
			db2.execSQL("UPDATE " + table + " SET status='0' WHERE name= '"+ title +"' ");
			db2.close(); 
			Toast.makeText(Selected_item.this, "Book Issued",
					Toast.LENGTH_LONG).show();
	            }
			else
			{
				Toast.makeText(Selected_item.this, "You cannot select books as per your membership",
						Toast.LENGTH_LONG).show();
			}
            }
        });
        
        
		
		
        
		
		}
		else{
			btn.setEnabled(true);
            btn.setOnClickListener(new View.OnClickListener(){
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i= new Intent(Selected_item.this,Membership.class);
					startActivity(i);
					btn.setEnabled(false);
					
				}
			});
			Toast.makeText(Selected_item.this, "you do not have membership",
					Toast.LENGTH_LONG).show();
		}

		
	}
}
