package com.example.backg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	DBAdapter dbAdapter;
	EditText txtUserName;
	EditText txtPassword;
	Button btnLogin;
	Button btnRegister;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtUserName = (EditText) findViewById(R.id.editText1);
		txtPassword = (EditText) findViewById(R.id.editText2);
		btnLogin = (Button) findViewById(R.id.button1);



		dbAdapter = new DBAdapter(this);
		dbAdapter.open();

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(txtUserName.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(txtPassword.getWindowToken(), 0);
				String username = txtUserName.getText().toString();
				String password = txtPassword.getText().toString();
				if (username.length() > 0 && password.length() > 0) {
					try {

						if (dbAdapter.Login(username, password)) {
							SharedPreferences shared_preferences;
							  SharedPreferences.Editor shared_preferences_editor;
							  String test_string = "";
							/*Toast.makeText(MainActivity.this,
									"Successfully Logged In", Toast.LENGTH_LONG)
									.show();*/
							  
							  shared_preferences = getSharedPreferences("shared_preferences_test",
								        MODE_PRIVATE);
							  shared_preferences_editor =shared_preferences.edit();
							  shared_preferences_editor.putString("email", username);
							  shared_preferences_editor.commit();
								//  test_string = shared_preferences.getString("test_key", username);

								  //Toast.makeText(getApplicationContext(), test_string, Toast.LENGTH_SHORT)
								      //  .show();

								  
							Intent i3= new Intent(MainActivity.this,MainLogin.class);
							startActivity(i3);
						} else {
							Toast.makeText(MainActivity.this,
									"Invalid username or password",
									Toast.LENGTH_LONG).show();
						}

					} catch (Exception e) {
						Toast.makeText(MainActivity.this, "Some problem occurred",
								Toast.LENGTH_LONG).show();

					}
				} else {
					Toast.makeText(MainActivity.this,
							"Username or Password is empty", Toast.LENGTH_LONG).show();
				}
			}
		});




			Button b= (Button) findViewById(R.id.button2);
			b.setOnClickListener(new View.OnClickListener(){
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i= new Intent(MainActivity.this,register.class);
					startActivity(i);
					
					
				}
			});
	}
}
