package com.example.backg;

public class Contact {

	// private variables
	int _id;
	String _status;
	String _name;
	byte[] _image;
	String _author;
	// Empty constructor
	public Contact() {

	}

	// constructor
	public Contact(int keyId, String name, byte[] image,String author,String status) {
	this._id = keyId;
	this._name = name;
	this._image = image;
	this._author=  author;
	this._status= status;

	}

	// constructor
	public Contact(String name, byte[] image,String author,String status) {
	this._name = name;
	this._image = image;
	this._author=  author;
	this._status= status;
	}

	// getting ID
	public int getID() {
	return this._id;
	}

	// setting id
	public void setID(int keyId) {
	this._id = keyId;
	}

	// getting name
	public String getName() {
	return this._name;
	}

	// setting name
	public void setName(String name) {
	this._name = name;
	}

	// getting phone number
	public byte[] getImage() {
	return this._image;
	}

	// setting phone number
	public void setImage(byte[] image) {
	this._image = image;
	}
	public String getAuthor() {
		return this._author;
		}
	
	public void setAuthor(String author) {
		this._author = author;
		}
	
	public String getstatus() {
		return this._status;
		}

		// setting id
		public void setstatus(String status) {
		
			if(status.equalsIgnoreCase("0"))
					status="N/A";
			else
				status="Available";
			
				
			
		this._status = status;
		}
	}
