package com.example.stickyhabits;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ViewHabitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_habit);
		Bundle extras = getIntent().getExtras(); 
		TextView testView = (TextView) findViewById(R.id.textView1);
		if (extras != null) {
		    testView.setText(extras.getString("name"));
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_view_habit, menu);
		return true;
	}
	
	/** Called when the user clicks the Statistic button */
	public void goHabitStatistic(View view) {
	    // Do something in response to button
		TextView testView = (TextView) findViewById(R.id.textView1);
		Bundle extras = getIntent().getExtras();
		Intent intent = new Intent(this, GlobalStatistics.class);
		intent.putExtra("name", testView.getText());
	    startActivity(intent);
	}

}
