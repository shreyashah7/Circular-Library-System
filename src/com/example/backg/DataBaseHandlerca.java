package com.example.backg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

public class DataBaseHandlerca 
{

// All Static variables
// Database Version
private static final int DATABASE_VERSION = 1;

// Database Name
private static final String DATABASE_NAME = "current.db";

// Contacts table name
private static final String TABLE_CONTACTS = "current";

// Contacts Table Columns names
private static final String KEY_ID = "id";
private static final String KEY_NAME = "name";
private static final String KEY_IMAGE = "image";
private static final String KEY_AUTHOR = "author";
private static final String KEY_STATUS = "status";
private SQLiteDatabase db;
File sdCrd=Environment.getExternalStorageDirectory();

public DataBaseHandlerca() {
	//super(context, "/sdcard"+DATABASE_NAME, null, DATABASE_VERSION);
	

}

// Creating Tables
/*
@Override
  public void onCreate(SQLiteDatabase db) {
String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_CONTACTS + "("
+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
+ KEY_IMAGE + " BLOB," + KEY_AUTHOR + " TEXT," + KEY_STATUS + " TEXT" + ")";
db.execSQL(CREATE_CONTACTS_TABLE);
}*/





// Adding new contact

public void addContact(Contact contact) {
	db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME, null,SQLiteDatabase.OPEN_READWRITE);	
//SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put(KEY_NAME, contact._name); // Contact Name
values.put(KEY_IMAGE, contact._image); // Contact Phone
values.put(KEY_AUTHOR, contact._author);
values.put(KEY_STATUS, contact._status);

// Inserting Row

db.insertOrThrow(TABLE_CONTACTS, null, values);

db.close(); // Closing database connection
}


// Getting single contact
Contact getContact(int id) {
	
	
	System.out.println("hello");
	
//	db = this.getReadableDatabase();

Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
KEY_NAME, KEY_IMAGE,KEY_AUTHOR,KEY_STATUS }, KEY_ID + "=?",
new String[] { String.valueOf(id) }, null, null, null, null);
if (cursor != null)
cursor.moveToFirst();

Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
cursor.getString(1),cursor.getBlob(1),cursor.getString(2),cursor.getString(3));

// return contact
return contact;

}

// Getting All Contacts
public List<Contact> getAllContacts() {
List<Contact> contactList = new ArrayList<Contact>();
// Select All Query

db=SQLiteDatabase.openDatabase(sdCrd+File.separator+DATABASE_NAME, null,SQLiteDatabase.OPEN_READONLY);	
//db = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
//String str[] = new String[]{KEY_ID,KEY_NAME,KEY_STATUS,KEY_AUTHOR,KEY_IMAGE};
//Cursor cr =db.query(TABLE_CONTACTS, str, null,null, null, null, null);
System.out.println(db.isOpen());
String selectQuery = "SELECT * FROM current";



Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
if (cursor.moveToFirst()) {
do {
Contact contact = new Contact();
contact.setID(Integer.parseInt(cursor.getString(0)));
contact.setName(cursor.getString(1));
contact.setImage(cursor.getBlob(2));
contact.setAuthor(cursor.getString(3));
contact.setstatus(cursor.getString(4));
// Adding contact to list
contactList.add(contact);
} while (cursor.moveToNext());

// close inserting data from database
db.close();
}
// return contact list
return contactList;

}

// Updating single contact
public int updateContact(Contact contact) {
//SQLiteDatabase db = this.getWritableDatabase();

ContentValues values = new ContentValues();
values.put(KEY_NAME, contact.getName());
values.put(KEY_IMAGE, contact.getImage());
values.put(KEY_AUTHOR, contact.getAuthor());
values.put(KEY_STATUS, contact.getstatus());
// updating row
return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
new String[] { String.valueOf(contact.getID()) });

}

// Deleting single contact
/*public void deleteContact(Contact contact) {
SQLiteDatabase db = this.getWritableDatabase();
db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
new String[] { String.valueOf(contact.getID()) });
db.close();
}*/

// Getting contacts Count
public int getContactsCount() {
String countQuery = "SELECT * FROM " + TABLE_CONTACTS +"order by name";
//SQLiteDatabase db = this.getReadableDatabase();
Cursor cursor = db.rawQuery(countQuery, null);
cursor.close();

// return count
return cursor.getCount();
}

//@Override
/*public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	// TODO Auto-generated method stub
	
}*/

/*@Override
public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	
}*/
}

