package com.example.backg;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlogin);
		Button b1= (Button) findViewById(R.id.button1);
		Button b2= (Button) findViewById(R.id.button2);
		//Button b3= (Button) findViewById(R.id.button3);
		Button b4= (Button) findViewById(R.id.button4);
		b1.setOnClickListener(onClickListener);
		b2.setOnClickListener(onClickListener);
		//b3.setOnClickListener(onClickListener);
		b4.setOnClickListener(onClickListener);
	}
	private OnClickListener onClickListener = new OnClickListener(){
		@Override
	public void onClick(final View v)
	{
		switch(v.getId()){
		case R.id.button1:
			Intent i1= new Intent(MainLogin.this,Category.class);
			startActivity(i1);
			break;
		case R.id.button2:
			Intent i2= new Intent(MainLogin.this,Membership.class);
			startActivity(i2);
			break;
		/*case R.id.button5:
			Intent i5= new Intent(Category.this,CurrentAffair_Activity.class);
			startActivity(i5);
			break;*/
		
		case R.id.button4:
			Intent i4= new Intent(MainLogin.this,Aboutus.class);
			startActivity(i4);
			break;
	}
		
	}		

	};


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
