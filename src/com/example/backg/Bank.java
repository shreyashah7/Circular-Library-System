package com.example.backg;

import java.io.File;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Bank  extends Activity{
	
	private Spinner spinner1, spinner2;
	private EditText accntno,password;
	  private Button btnSubmit;
	  private SQLiteDatabase db;
	 
		File sdCrd=Environment.getExternalStorageDirectory();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		accntno = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		btnSubmit = (Button) findViewById(R.id.button1);
		
		SharedPreferences shared_preferences;
		  SharedPreferences.Editor shared_preferences_editor;
		  String test_string = "";

		 
		 shared_preferences = getSharedPreferences("shared_preferences_test",
			        MODE_PRIVATE);
		final String membership=shared_preferences.getString("m", "Default");
		
		btnSubmit.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
				  
				  db=SQLiteDatabase.openDatabase(sdCrd+File.separator+"bank.db", null,SQLiteDatabase.OPEN_READWRITE);	
				  Cursor m = db.rawQuery("SELECT * FROM bank WHERE acc_no = '" + accntno.getText() + "' and password = '"+password.getText() +"'" , null);
				  if(m.moveToFirst()) {
					  do {
				  
				  if(m.getCount() > 0)
				  {
					  
					  if(membership.equalsIgnoreCase("diamond"))
					  {
						  int s=  (Integer.parseInt(m.getString(3)));
						  
						  int remain=s-3000;
						  db.execSQL("UPDATE bank SET balance= " + remain+ " WHERE acc_no = '" + accntno.getText() + "' and password = '"+password.getText() +"' and name = '"+spinner1.getSelectedItem() +"'");
							
					  Toast.makeText(Bank.this,
								"You have been redirected to " + spinner1.getSelectedItem() + "bank and as per your selected membership amount is deducted from your account",
									Toast.LENGTH_LONG).show();
					  }
				  }
					  }while(m.moveToNext());
					  db.close(); 
				  }
					  

				  else
				  {
					  Toast.makeText(Bank.this,
								"Enter Valid Account No or Password",
									Toast.LENGTH_SHORT).show();
							    
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
