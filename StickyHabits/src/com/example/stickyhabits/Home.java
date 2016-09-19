package com.example.stickyhabits;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Home extends Activity {
	
	//private static String fileName = "StickyHabits_Habits";
	private String defaultHabits [] = new String [] {"Loose Weigth", "Quit Smoking", "Quit Drinking", "Swimming", "30 Mins Walking", "Customize Habit"}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		//File file = new File(this.getFilesDir(), fileName);
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	/** Called when the user clicks the My Goals button */
	public void myGoals(View view) {
	    // Do something in response to button
		if(Session.lastHabit != null) {
			questionaryDeploy();
		} else {
			Intent intent = new Intent(this, MyGoals.class);
		    startActivity(intent);
		}
	}
	
	/** Called when the user clicks the Add Habit button */
	public void chooseHabit(View view) {
		
		Dialog myDialog = new Dialog(this);
		ScrollView scrollView = new ScrollView(this);
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(1);
		
		for(int i = 0; i < defaultHabits.length; ++i) {
			Button button = new Button(this);
			button.setId(i);
			button.setText(defaultHabits[i]);
			button.setOnClickListener(myOnlyHandler);
			linearLayout.addView(button);
		}
		scrollView.addView(linearLayout);
		myDialog.setContentView(scrollView);
        myDialog.setTitle("Select a Habit:");
        myDialog.setCancelable(true);
		myDialog.show();
		
	    // Do something in response to button
		//Intent intent = new Intent(this, Home.class);
	    //startActivity(intent);
	}
	
	private View.OnClickListener myOnlyHandler = new View.OnClickListener() {
		
        public void onClick(View v) {
        	Button button = (Button) v;
        	if(button.getText() == "Customize Habit") {
        		Intent intent = new Intent(Home.this, CustomizeHabit.class);
        		startActivity(intent);
        	} else {
        		
        	}
		}
    };
	
	/** Called when the user clicks the User Profile button */
	public void userProfile(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, UserProfile.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Buy Avatar button */
	public void buyAvatar(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, BuyAvatar.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Global Statistics button */
	public void globalStatistics(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, GlobalStatistics.class);
	    startActivity(intent);
	}
	/** Called when the user clicks the Ranking button */
	public void ranking(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, Ranking.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Friends button */
	public void friends(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, Friends.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Settings button */
	public void settings(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, Settings.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Logout button */
	public void logout(View view) {
	    // Do something in response to button
		setContentView(R.layout.test);
		//Intent intent = new Intent(this, MainActivity.class);
	    //startActivity(intent);
	}
	
	public void questionaryDeploy() {
		
		Intent intent = new Intent(this, AnswerQuestionary.class);
		intent.putExtra("goal", Session.lastHabit.getGoal());
		Session.lastHabit = null;
		startActivity(intent);
		
	}

}
