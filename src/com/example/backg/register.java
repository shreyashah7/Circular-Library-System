package com.example.backg;

import com.example.backg.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class register extends Activity {
	DBAdapter dbAdapter;
	EditText txtUserName;
	EditText txtPassword;
	Button btnRegister;
	EditText address;
	EditText city;
	EditText ph_no;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		txtUserName = (EditText) findViewById(R.id.editText1);
		txtPassword = (EditText) findViewById(R.id.editText2);
		address = (EditText) findViewById(R.id.editText4);
		city = (EditText) findViewById(R.id.editText5);
		ph_no = (EditText) findViewById(R.id.editText6);
		btnRegister = (Button) findViewById(R.id.button1);
		dbAdapter = new DBAdapter(this);
		dbAdapter.open();
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(txtUserName.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(txtPassword.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(address.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(city.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(ph_no.getWindowToken(), 0);
				
				try {

					String username = txtUserName.getText().toString();
					String password = txtPassword.getText().toString();
					String address = txtPassword.getText().toString();
					String city = txtPassword.getText().toString();
					String ph_no = txtPassword.getText().toString();
					long i = dbAdapter.register(username, password,address,city,ph_no);
					if(i == 1)
						Toast.makeText(register.this, "You have successfully registered",Toast.LENGTH_LONG).show();
					

				} catch (SQLException e) {
					Toast.makeText(register.this, "you have already registered",
							Toast.LENGTH_LONG).show();

				}
				
			}
		});
		
	}
 
}