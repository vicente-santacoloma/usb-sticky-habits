package com.example.stickyhabits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class UserProfile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		TextView usernameTextView = (TextView) findViewById(R.id.username);
		TextView scoreTextView    = (TextView) findViewById(R.id.score);
		usernameTextView.setText(Session.user.getUsername());
		scoreTextView.setText("Score: " + Integer.toString(245));
		
		ImageButton profPicture = (ImageButton) findViewById(R.id.imageButton1);
		profPicture.setImageResource(Session.profilePicture);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_profile, menu);
		return true;
	}
	
	/** Called when the user clicks the image button */	
	public void selectAvatar(View view){
		Intent intent = new Intent(this, SelectAvatar.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the save button */
	public void save(View view) {
		
		String error = "";
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
		Database db = new Database();
		db.getUsers(users);
		
		for(int i = 0; i < users.size(); ++i) {
			if(users.get(i).getUsername().equals(Session.user.getUsername())) {
				user = users.get(i);
			}
		}
		
		EditText eText = (EditText) findViewById(R.id.email);
		String email = eText.getText().toString();
		eText = (EditText) findViewById(R.id.old_password);
		String oldPassword = eText.getText().toString();
		eText = (EditText) findViewById(R.id.password);
		String newPassword = eText.getText().toString();
		eText = (EditText) findViewById(R.id.repeat_password);
		String repeatPassword = eText.getText().toString();
		
		if(!email.isEmpty() || !oldPassword.isEmpty() || !newPassword.isEmpty() || !repeatPassword.isEmpty()) {
			
			Dialog myDialog = new Dialog(this);
			TextView text = new  TextView(this);
			
			if(email.isEmpty()) {
				error += "Must type an email\n";
			}
			if(oldPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty()) {
				error += "Must type the password\n";
			}
			if(!(email.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$"))) {
				error += "Invalid email\n";
			}
			if(!oldPassword.isEmpty() && !oldPassword.equals(user.getPassword())) {
				error += "The password does not match the user's password\n";
			}
			if(!newPassword.equals(repeatPassword)){
				error += "The passwords must match\n";
			}
			
			if(error.isEmpty()) {
				user.setEmail(email);
				user.setPassword(newPassword);
				db.saveUsers(users);
				text.setText("User successfully saved");
				myDialog.setContentView(text);
				myDialog.show();
				
			} else {
				text.setText(error);
				myDialog.setContentView(text);
				myDialog.show();
			}
		}
	}
	
}
