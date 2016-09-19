package com.example.stickyhabits;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MyGoals extends Activity {

	private String defaultGoals [] = new String [] {"Loose Weigth", "Quit Smoking", "Quit Drinking", 
													"Swimming", "30 Mins Walking", "Daily Bible Reading", 
													"Daily Yoga", "Eat Fruits", "Write Blogs",
													"Go to Church on Sundays"};
	
	private View.OnClickListener myOnlyHandler = new View.OnClickListener(){
        public void onClick(View v) {
        	Button button = (Button) v;
        	Intent intent = new Intent(MyGoals.this, ViewHabitActivity.class);
        	intent.putExtra("name", button.getText());
        	intent.putExtra("user", Session.user.getUsername());
    	    startActivity(intent);
		}
    };
	
    private View.OnClickListener deleteHandler = new View.OnClickListener(){
        public void onClick(View v) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.my_goals_linear_layout);
        LinearLayout ll = (LinearLayout) findViewById(v.getId());
        linearLayout.removeView(ll);
		}
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_goals);
		displayMyGoals();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_goals, menu);
		return true;
	}
	
	private void displayMyGoals() {
	    // Do something in response to button
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.my_goals_linear_layout);
		
		ArrayList<Habit> habits = new ArrayList<Habit>();
		Database db = new Database();
		db.getHabits(habits, Session.user);
		
		for(int i = 0; i < habits.size(); ++i) {
			LinearLayout ll = new LinearLayout(this);
			ll.setId(i);
			LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.MATCH_PARENT,
	                LayoutParams.WRAP_CONTENT, 1.0f);
			
			Button button = new Button(this);
			button.setId(i);
			button.setLayoutParams(params);
			//button.setBackgroundColor(0xF2D03B);
			button.setText(habits.get(i).getGoal());
			button.setOnClickListener(myOnlyHandler);
			button.setBackgroundResource(R.drawable.label_shape);
			button.setTextColor(getResources().getColor(android.R.color.white));
			ll.addView(button);
			Button button2 = new Button(this);
			button2.setId(i);
			//button.setBackgroundColor(0xF2D03B);
			button2.setText(R.string.delete);
			button2.setBackgroundResource(R.drawable.delete_goal_shape);
			button2.setOnClickListener(deleteHandler);
			button2.setTextColor(getResources().getColor(android.R.color.white));
			ll.addView(button2);
			linearLayout.addView(ll);
		}
	}
	
	
    
}
