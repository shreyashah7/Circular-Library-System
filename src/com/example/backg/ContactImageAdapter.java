package com.example.backg;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactImageAdapter extends ArrayAdapter<Contact>{
Context context;
int layoutResourceId;
ArrayList<Contact> data=new ArrayList<Contact>();
public ContactImageAdapter(Context context, int layoutResourceId, ArrayList<Contact> data) {
super(context, layoutResourceId, data);
this.layoutResourceId = layoutResourceId;
this.context = context;
this.data = data;
}

@Override
public View getView(int position, View convertView, ViewGroup parent) {
View row = convertView;
ImageHolder holder = null;
if(row == null)
{
LayoutInflater inflater = ((Activity)context).getLayoutInflater();
row = inflater.inflate(layoutResourceId, parent, false);
holder = new ImageHolder();
holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
holder.txtauthor = (TextView)row.findViewById(R.id.txtauthor);
holder.txtstatus = (TextView)row.findViewById(R.id.txtstatus);
//holder.add_to_q = (Button)row.findViewById(R.id.add_to_q) ; 
row.setTag(holder);
}
else
{
holder = (ImageHolder)row.getTag();
}
Contact picture = data.get(position);
holder.txtTitle.setText(picture ._name);
//convert byte to bitmap take from contact class
byte[] outImage=picture._image;
ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
Bitmap theImage = BitmapFactory.decodeStream(imageStream);
holder.imgIcon.setImageBitmap(theImage);
holder.txtauthor.setText(picture ._author);
holder.txtstatus.setText(picture._status);
/*holder.add_to_q.setOnClickListener(new OnClickListener(){
		
	@Override	
	public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i= new Intent(context,MainLogin.class);
			context.startActivity(i);	
		}
	});*/	


return row;
}

static class ImageHolder
{
ImageView imgIcon;
TextView txtTitle;
TextView txtauthor;
TextView txtstatus;
Button add_to_q;
}
}
