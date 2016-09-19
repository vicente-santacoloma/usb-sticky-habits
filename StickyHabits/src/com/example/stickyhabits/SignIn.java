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

public class SignIn extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sign_in, menu);
		return true;
	}
	
	/** Called when the user clicks the Sign In button */
	public void signIn(View view) {
	    // Do something in response to button
		String username = ((EditText) findViewById(R.id.username)).getText().toString();
		String password = ((EditText) findViewById(R.id.password)).getText().toString();
		
		User user = new User(username);
		Database db = new Database();
		boolean exists = db.getUser(user);
		
		if(exists && user.getPassword().equals(password)) {
			Session.user = user;
			Session.profilePicture = R.drawable.ic_main_logo;
			Intent intent = new Intent(this, Home.class);
			startActivity(intent);
		} else {
			TextView texto = (TextView) findViewById(R.id.errorPass);
			texto.setText("Wrong Username/Email or Password");
		}

	}
	
	/*
	private int checkUser (String username, String pass) {
        try {
            FileInputStream fIn = openFileInput ( "Usuarios" ) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
            	String[] data = readString.split(" ");
                if((data[0].compareTo(username)==0 || data[1].compareTo(username)==0) && data[2].compareTo(pass)==0){
                	return 1;
                }
                readString = buffreader.readLine ( ) ;
            }
            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        return 0;
    }*/
}
