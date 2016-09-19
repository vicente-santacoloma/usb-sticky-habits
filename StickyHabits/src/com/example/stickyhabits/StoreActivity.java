package com.example.stickyhabits;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class StoreActivity extends Activity {

	private Dialog myDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store, menu);
		return true;
	}

	public void buy(View v) {
		// Do something in response to button
		myDialog = new Dialog(v.getContext());
		LinearLayout linearLayout = new LinearLayout(v.getContext());
		linearLayout.setOrientation(1);
		Button button1 = new Button(v.getContext());
		button1.setId(99);
		button1.setText("Buy avatar");
		button1.setOnClickListener(buyHandler);
		linearLayout.addView(button1);
		myDialog.setContentView(linearLayout);
        myDialog.setTitle("Are you sure?");
        myDialog.setCancelable(true);
		myDialog.show();
	}
	
    private View.OnClickListener buyHandler = new View.OnClickListener() {
		
        public void onClick(View v) {
    
    		Session.profilePicture = R.drawable.ic_male_face_a4_profile;
    		myDialog.dismiss();
    		//Intent intent = new Intent(v.getContext(), UserProfile.class);
    		//startActivity(intent);
        }
    };
}
