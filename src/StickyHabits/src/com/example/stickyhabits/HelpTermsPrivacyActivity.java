package com.example.stickyhabits;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class HelpTermsPrivacyActivity extends Activity {
	private String helpBody="Common questions and answers\n How can I create a new habit?\n" +
			"1.In the screen press the button add, the one with the sum simbol(+).\n 1.1.If the habit" +
			"that you want to change or acquire is on the default options, press it.\n 2.Complete the " +
			"questionary form.\n 1.2.If the habit that you want to change or acquire isn't on the " +
			"default options press the \"Customize habit\" option.\n 2.Complete the forms.";
	
	private String termsBody="1.Your Acceptance: \nBy using the Sticky Habit App" +
			"you signify your agreement to (1)terms and conditions(the \"Terms of Service\")" +
			", (2)Sticky Habits Privacy Policy, found in Settings/PrivacyPolicy." +
			" If you do not agree to any of these terms, please do not use the Service.\n\n" +
			"2.Service: \nThese Terms of Service apply to all users of Service,...";
	
	private String privacyBody="Last modified: February 25, 2013.\n\n" +
			"These are many different ways you can use our services - to add a habit you might want to change" +
			" or acquire, to search your friends and share informations with them....";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help_terms_privacy);
		Bundle extras = getIntent().getExtras(); 
		TextView textView = (TextView) findViewById(R.id.textView1);
		TextView textView2 = (TextView) findViewById(R.id.textView2);
		if(extras != null){
			textView.setText(extras.getString("from"));
		}
		if((extras.getString("from")).compareTo("Help")==0){
			textView2.setText(helpBody);
		}else if((extras.getString("from")).compareTo("Terms of Service")==0){
			textView2.setText(termsBody);
		}else if((extras.getString("from")).compareTo("Privacy Policy")==0){
			textView2.setText(privacyBody);
		}else{
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help_terms_privacy, menu);
		return true;
	}

}
