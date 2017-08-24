
package com.example.backg;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class Category extends Activity {
 
	Button button;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categ);
		Button b1= (Button) findViewById(R.id.button1);
		Button b2= (Button) findViewById(R.id.button2);
		Button b3= (Button) findViewById(R.id.button3);
		Button b4= (Button) findViewById(R.id.button4);
		Button b5= (Button) findViewById(R.id.button5);
		b1.setOnClickListener(onClickListener);
		b2.setOnClickListener(onClickListener);
		b3.setOnClickListener(onClickListener);
		b4.setOnClickListener(onClickListener);
		b5.setOnClickListener(onClickListener);
	}
	private OnClickListener onClickListener = new OnClickListener(){
		@Override
	public void onClick(final View v)
	{
		switch(v.getId()){
		case R.id.button1:
			Intent i1= new Intent(Category.this,MainActivity1.class);
			startActivity(i1);
			break;
		case R.id.button2:
			Intent i2= new Intent(Category.this,Health_Activity.class);
			startActivity(i2);
			break;
		case R.id.button5:
			Intent i5= new Intent(Category.this,CurrentAffair_Activity.class);
			startActivity(i5);
			break;
		case R.id.button3:
			Intent i3= new Intent(Category.this,Religion_Activity.class);
			startActivity(i3);
			break;
		case R.id.button4:
			Intent i4= new Intent(Category.this,Fict_Activity.class);
			startActivity(i4);
			break;
	}
		
	}		

	};

}


