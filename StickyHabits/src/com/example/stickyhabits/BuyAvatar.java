package com.example.stickyhabits;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class BuyAvatar extends Activity {
	
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
		R.drawable.ic_male_face_n3};

	private int select;
	
	private AlertDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_avatar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_buy_avatar, menu);
		return true;
	}
	
	/** Called when the user clicks the Image Button */
	public void chooseAvatar(View view) {
		setContentView(R.layout.store);
	}
	
	public void buy(View v) {
    		
		Session.profilePicture = R.drawable.ic_male_face_a4_profile;
		Intent intent = new Intent(this, UserProfile.class);
		startActivity(intent);
    }
	
	public void require1(View v) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
		builder.setMessage("You require 4000 points to choose Halloween Avatars");
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
    }
	
	public void require2(View v) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
		builder.setMessage("You require 7000 points to choose Monster Avatars");
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
    }
	
	public void require3(View v) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
		builder.setMessage("You require 15000 points to choose Creature Avatars");
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
    }
    
    private final class CancelOnClickListener implements DialogInterface.OnClickListener {
	  public void onClick(DialogInterface dialog, int which) {
	    Toast.makeText(getApplicationContext(), "Activity will continue",
	        Toast.LENGTH_LONG).show();
	  }
	}

	private final class OkOnClickListener implements DialogInterface.OnClickListener {
	  
		public void onClick(DialogInterface dialog, int which) {
			Session.profilePicture = R.drawable.ic_male_face_a4_profile;
        	dialog.dismiss();
		}
	}
    
    

}
