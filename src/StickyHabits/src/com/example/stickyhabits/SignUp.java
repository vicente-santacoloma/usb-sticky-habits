package com.example.stickyhabits;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class SignUp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sign_up, menu);
		return true;
	}
	
	/** Called when the user clicks the Create button */
	public void create(View view) {
	    // Do something in response to button
		TextView text = (TextView) findViewById(R.id.error);
		text.setText("");
		
		String username = ((EditText) findViewById(R.id.username)).getText().toString();
		String email = ((EditText) findViewById(R.id.email)).getText().toString();
		String password = ((EditText) findViewById(R.id.password)).getText().toString();
		String repeatePassword = ((EditText) findViewById(R.id.repeat_password)).getText().toString();
		
		User user = new User (username, email, password, repeatePassword);
		
		boolean check = checkParams(user);
		Database db = new Database();
		
		if(check && !db.getUser(new User(user.getUsername()))) {
			db.saveUser(user);
			Session.user = user;
			Session.profilePicture = R.drawable.ic_main_logo;
			Intent intent = new Intent(this, Home.class);
			startActivity(intent);
		}

	}
	
	private boolean checkParams(User user) {
	
		TextView text = (TextView) findViewById(R.id.error);
		String username = user.getUsername();
		String email = user.getEmail();
		String password = user.getPassword();
		String repeatPassword = user.getRepeat_password();
		String errorMessage = "";
		boolean check = true;
		
		if(username.isEmpty()) {
			errorMessage += "Must type an username\n";
			check = false;
		}
		if(password.isEmpty() || repeatPassword.isEmpty()) {
			errorMessage += "Must type a password\n";
			check = false;
		}
		if(email.isEmpty()) {
			errorMessage += "Must type an email\n";
			check = false;
		}
		if(!email.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")) {
			errorMessage += "Invalid email\n";
			check = false;
		}
		if(!password.equals(repeatPassword)) {
			errorMessage += "The passwords must match\n";
			check = false;
		}
		if(!check) {
			text.setText(errorMessage);
		}

		return check;
	}
	
	/** Called when the user clicks the Cancel button */
	public void cancel(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}
	
	/*
	private int checkUser (String username, String email) {
        try {
            FileInputStream fIn = openFileInput ( "Usuarios" ) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
            	String[] data = readString.split(" ");
                if(data[0].compareTo(username)==0 && data[1].compareTo(email)!=0 ){
                	return 1;
                }
                if(data[1].compareTo(email)==0 && data[0].compareTo(username)!=0){
                	return 2;
                }
                if(data[1].compareTo(email)==0 && data[0].compareTo(username)==0){
                	return 3;
                }
                readString = buffreader.readLine ( ) ;
            }
            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        return 0;
    }
	
	
	if(     pass1.getText().length()!=0 && pass2.getText().length()!=0 &&
			pass1.getText().toString().compareTo(pass2.getText().toString())==0 &&
			user.getText().length()!=0 && email.getText().length()!=0 ){
		int check = checkUser(user.getText().toString(),email.getText().toString());
		if(check ==0){
			if(email.getText().toString().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")){
				try{
					FileOutputStream fOut = openFileOutput("Usuarios", MODE_APPEND);
					String line = user.getText().toString()+" "+
							  email.getText().toString()+" "+
					          pass1.getText().toString()+"\n";
					fOut.write(line.getBytes());
					Intent intent = new Intent(this, Home.class);
					intent.putExtra("user", user.getText().toString());
					startActivity(intent);
				}catch (IOException ioe) {
					ioe.printStackTrace ( ) ;
				}
			}else{
				text.setText(text.getText().toString()+"Invalid Email");
			}
		}
		if(check==1 || check==3){
			text.setText(text.getText().toString()+"Username already exists\n");
		}
		if(check==2 || check==3){
			text.setText(text.getText().toString()+"Email already exists\n");
		}
	}else{
		if(pass1.getText().length()!=0 && pass2.getText().length()!=0 &&
		   pass1.getText().toString().compareTo(pass2.getText().toString())!=0	){
			text.setText(text.getText().toString()+"The passwords must match\n");
		}
		if(user.getText().length()==0){
			text.setText(text.getText().toString()+"Must type an Username\n");
		}
		if(email.getText().length()==0){
			text.setText(text.getText().toString()+"Must type an Email\n");
		}else{
			if(!(email.getText().toString().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$"))){
				text.setText(text.getText().toString()+"Invalid Email\n");
			}
		}
		if(pass1.getText().length()==0 || pass2.getText().length()==0){
			text.setText(text.getText().toString()+"Must type the password");
		}
	}
	*/

}
