package com.example.stickyhabits;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SelectAvatar extends Activity {
private int columns = 3;
	
	private int maleFace [] = new int [] {
		R.drawable.ic_male_face_a4,
		R.drawable.ic_male_face_b1,
		R.drawable.ic_male_face_b3,
		R.drawable.ic_male_face_b5,
		R.drawable.ic_male_face_d4,
		R.drawable.ic_male_face_d5,
		R.drawable.ic_male_face_e1,
		R.drawable.ic_male_face_e2,
		R.drawable.ic_male_face_e4,
		R.drawable.ic_male_face_n1,
		R.drawable.ic_male_face_n2,
		R.drawable.ic_male_face_n3};

	private int select;
	
	private Dialog myDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_avatar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_avatar, menu);
		return true;
	}
	
	/** Called when the user clicks the Image Button */
	public void chooseAvatar(View view) {
	    // Do something in response to button
		setContentView(R.layout.activity_select_avatar);
		LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.AvatarLinearLayout);
		
		int avatar [] = null;
		
		switch(view.getId()) {
			case R.id.male      : avatar = maleFace; break;
			case R.id.female    : avatar = null; break;
			case R.id.halloween : avatar = null; break;
			case R.id.monster   : avatar = null; break;
			case R.id.creature  : avatar = null; break;
		}

		int linearLayoutNumber = avatar.length/columns;
		if(avatar.length % columns != 0) {
			++linearLayoutNumber;
		}
		
		int imageButtonElements = columns;
		int k = 0;
		
		for(int i = 0; i < linearLayoutNumber; ++i) {
			LinearLayout linearLayout = new LinearLayout(mainLinearLayout.getContext());
			linearLayout.setGravity(Gravity.CENTER);
			if(i == linearLayoutNumber - 1) {
				if(avatar.length % columns != 0) {
					imageButtonElements = avatar.length % columns;
				}
			}
			for(int j = 0; j < imageButtonElements; ++j) {
				MyImageButton imageButton = new MyImageButton(linearLayout.getContext());
				imageButton.setImageResource(avatar[k]);
				imageButton.setOnClickListener(myOnlyHandler);
				linearLayout.addView(imageButton);
				++k;
			}
			mainLinearLayout.addView(linearLayout);
		}
		
		
		//Intent intent = new Intent(this, UserProfile.class);
	    //startActivity(intent);
	}
	
	private View.OnClickListener myOnlyHandler = new View.OnClickListener() {
		
        public void onClick(View v) {
        	MyImageButton button = (MyImageButton) v;
        	select = button.getImageResource();
        	myDialog = new Dialog(v.getContext());
    		LinearLayout linearLayout = new LinearLayout(v.getContext());
    		linearLayout.setOrientation(1);
   			Button button1 = new Button(v.getContext());
   			button1.setId(99);
   			button1.setText("Select avatar");
   			button1.setOnClickListener(buyHandler);
   			linearLayout.addView(button1);
    		myDialog.setContentView(linearLayout);
            myDialog.setTitle("Are you sure?");
            myDialog.setCancelable(true);
    		myDialog.show();
        }
    };
    
    private View.OnClickListener buyHandler = new View.OnClickListener() {
		
        public void onClick(View v) {
        	Session.profilePicture = select;
        	select = 0;
        	myDialog.dismiss();
        	Intent intent = new Intent(v.getContext(), Home.class);
    	    startActivity(intent);
        }
    };

}

