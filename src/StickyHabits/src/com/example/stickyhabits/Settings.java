package com.example.stickyhabits;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Settings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_settings, menu);
		return true;
	}

	/** Called when the user clicks the Account button */
	public void userProfile(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, UserProfile.class);
		startActivity(intent);
	}
	
	/** Called when the user clicks the Account button */
	public void help_terms_privacy(View view) {
		// Do something in response to button
		Button button = (Button) view;
		Button button2 = (Button) findViewById(R.id.button2);
		Button button4 = (Button) findViewById(R.id.button4);
		Button button5 = (Button) findViewById(R.id.button5);
		Intent intent = new Intent(this, HelpTermsPrivacyActivity.class);
		if(button.getId() == button2.getId()){
			intent.putExtra("from", "Help");
		}else if(button.getId() == button4.getId()){
			intent.putExtra("from", "Terms of Service");
		}else if(button.getId() == button5.getId()){
			intent.putExtra("from", "Privacy Policy");
		}else{
			
		}
		startActivity(intent);
	}
	

}
