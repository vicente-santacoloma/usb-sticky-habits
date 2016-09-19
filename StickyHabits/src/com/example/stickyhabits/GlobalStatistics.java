package com.example.stickyhabits;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class GlobalStatistics extends Activity {
	private String rest = " Graphic Statistics";
	private String global = "Global";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_global_statistics);
		Bundle extras = getIntent().getExtras(); 
		Button editText = (Button) findViewById(R.id.editText1);
		ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton1);
		if (extras != null) 
		{
		    editText.setText(extras.getString("name")+rest);
		    imageButton.setVisibility(0);
		}
		else{
			editText.setText(global+rest);
			imageButton.setVisibility(4);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_global_statistics, menu);
		return true;
	}
	
	/** Called when the user clicks the Home button */
	public void home(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, Home.class);
	    startActivity(intent);
	}

}
