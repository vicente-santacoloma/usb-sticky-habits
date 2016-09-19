package com.example.stickyhabits;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AnswerQuestionary extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_questionary);
		Intent intent = getIntent();
		String goal = intent.getStringExtra("goal");
		generateQuestionary(goal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_answer_questionary, menu);
		return true;
	}
	
	/** Called when the user clicks the Submit button */
	public void submit(View view) {
	    // Do something in response to button
		//Bundle extras = getIntent().getExtras();
		Intent intent = new Intent(this, Home.class);
		//intent.putExtra("user", extras.getString("user"));
	    startActivity(intent);
	}
	
	private void generateQuestionary(String goal) {
		
		Habit habit = new Habit(goal);
		habit.setUser(Session.user);
		Database db = new Database();
		boolean exists = db.getHabit(habit);
		LinearLayout answersLayout = (LinearLayout) findViewById(R.id.answers_layout);
		TextView goalTextView = (TextView) findViewById(R.id.goal);

		if (exists) {
			
			TextView questionTextView = null;
			EditText answerEditText = null;
			LinearLayout questionLayout = null;
			RadioGroup radioGroup = null;
			RadioButton radioButton = null;
			
			goalTextView.setText(habit.getGoal());
			ArrayList<Question> questions = habit.getQuestions();
			Question question = null;
			
			for(int i = 0; i < questions.size(); ++i) {
				
				question = questions.get(i);
				
				questionLayout = new LinearLayout(answersLayout.getContext());
				questionLayout.setOrientation(1);
				
				if(i % 2 == 0) {
					questionLayout.setBackgroundResource(R.drawable.element_odd_shape);
				} else {
					questionLayout.setBackgroundResource(R.drawable.element_odd_shape);
				}
				
				questionTextView = new TextView(questionLayout.getContext(), null, android.R.attr.textAppearanceLarge);
				questionTextView.setText(question.getQuestion());
				LayoutParams params = new LinearLayout.LayoutParams(
		                LayoutParams.MATCH_PARENT,
		                LayoutParams.WRAP_CONTENT, 1.0f);
				questionTextView.setLayoutParams(params);
				questionLayout.addView(questionTextView);
				
				
				if(question instanceof NumericQuestion) {
					answerEditText = new EditText(questionLayout.getContext());
					answerEditText.setLayoutParams(params);
					answerEditText.setId(i);
					answerEditText.setHint("Insert numeric value" + " between 0 and " + ((NumericQuestion) question).getContributionValueRank());
					answerEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
					questionLayout.addView(answerEditText);
				} else {
					
					radioGroup = new RadioGroup(questionLayout.getContext());
					radioGroup.setId(i);
					
					ArrayList<Answer> answers = ((ChoiceQuestion) question).getAnswers();
					for(int j = 0; j < answers.size(); ++j) {
						radioButton = new RadioButton(radioGroup.getContext());
						radioButton.setText(answers.get(j).getAnswer());
						radioButton.setChecked(false);
						radioGroup.addView(radioButton);
					}
					questionLayout.addView(radioGroup);
				}
				
				answersLayout.addView(questionLayout);
			}
			
		} else {
			goalTextView.setText("Goal not available");
		}
		
	}

}
