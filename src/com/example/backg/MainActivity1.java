package com.example.backg;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.backg.ContactImageAdapter.ImageHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity1 extends Activity {

	ArrayList<Contact> imageArry = new ArrayList<Contact>();
	public  ArrayList<String> Name = new ArrayList<String>();
	ContactImageAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		
		DataBaseHandler db = new DataBaseHandler();
		// get image from drawable
		Bitmap image = BitmapFactory.decodeResource(getResources(),
		R.drawable.i9);

		// convert bitmap to byte
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte imageInByte[] = stream.toByteArray();
		/**
		* CRUD Operations
		* */
		// Inserting Contacts
		//Log.d("Insert: ", "Inserting ..");
	//	db.addContact(new Contact("BrookBook", imageInByte,"Jim arrosky","1"));
		// display main List view bcard and contact name

		// Reading all contacts from database
		List<Contact> contacts = db.getAllContacts();
		for (Contact cn : contacts) {
		String log = "ID:" + cn.getID() + " Name: " + cn.getName()
		+ " ,Image: " + cn.getImage() + "author:" + cn.getAuthor() + "status:" + cn.getstatus();

		// Writing Contacts to log
		Log.d("Result: ", log);
		//add contacts data in arrayList
		imageArry.add(cn);

		}
		
		adapter = new ContactImageAdapter(this, R.layout.screen_list,
		imageArry);
		final ListView dataList = (ListView) findViewById(R.id.list);
		
		dataList.setAdapter(adapter);
		
		dataList.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1,
                    int pos, long id) {
                // TODO Auto-generated method stub
            	
            	//i1.putExtra("key_name" , get(pos));
            	TextView t= (TextView)findViewById(R.id.txtTitle);
            	TextView t1= (TextView)findViewById(R.id.txtauthor);
            	
           /*added*/ 	
            	
            	
                String s=t.getText().toString().trim();
                String s1=t.getText().toString().trim();
                Intent i1= new Intent(MainActivity1.this,Selected_item.class);
                i1.putExtra("title", s);
                i1.putExtra("tablename", "comics");
                i1.putExtra("dbname", "comics.db");
               // Toast.makeText(Health_Activity.this,s,Toast.LENGTH_LONG).show();
    			startActivity(i1);
    			
               // Log.v("long clicked","pos: " + pos);

              //  return true;
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

