package com.example.stickyhabits;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomizeHabit extends Activity {

	private Habit habit;
	private User user;
	private ArrayList<Integer> fillQuestions;
	private SparseArray<ArrayList<Integer>> fillAnswers;
	private HashMap<String, Integer> hashViewID;
	private int question = 0;
	private int viewID = 0;	
	private boolean checkForward = true;
	private boolean checkBack = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		user = Session.user;//new User(intent.getStringExtra("user"));
		hashViewID = new HashMap<String,Integer>();
		fillQuestions = new ArrayList<Integer>();
		fillAnswers = new SparseArray<ArrayList<Integer>>();
		setContentView(R.layout.activity_customize_habit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_customize_habit, menu);
		return true;
	}
	
	/** Called when the user clicks the Home button */
	public void home(View view) {
	    // Do something in response to button
		//Bundle extras = getIntent().getExtras();
		Intent intent = new Intent(this, Home.class);
		//intent.putExtra("user", extras.getString("user"));
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Right Arrow button */
	public void forward(View view) {
	    // Do something in response to button
		
		if(checkForward) {
			EditText goal = (EditText) findViewById(R.id.goal);
			EditText start_date = (EditText) findViewById(R.id.start_date);
			EditText duration = (EditText) findViewById(R.id.duration);
			Spinner spinner_difficulty = (Spinner) findViewById(R.id.spinner_difficulty);
			Spinner spinner_questionary_frequency = (Spinner) findViewById(R.id.spinner_questionary_frequency);
			boolean showDialog = false;
			
			String exception = "";

			if(goal.getText().toString().length() == 0) {
				exception = exception + "You must provide a goal.\n";
				showDialog = true;
			}
			if(start_date.getText().toString().length() == 0) {
				exception = exception + "You must provide a start date.\n";
				showDialog = true;
			}
			if(duration.getText().toString().length() == 0) {
				exception = exception + "You must provide a duration.\n";
				showDialog = true;
			}
			if(spinner_difficulty.getSelectedItemPosition() == 0) {
				exception = exception + "You must select a difficulty.\n";
				showDialog = true;
			}
			if(spinner_questionary_frequency.getSelectedItemPosition() == 0) {
				exception = exception + "You must select a frequency.\n";
				showDialog = true;
			}
			
			if(showDialog) {
				Dialog myDialog = new Dialog(this);
				TextView textViewException = new TextView(this); 
				textViewException.setText(exception);
				myDialog.setContentView(textViewException);
		        myDialog.setTitle("There was a problem");
		        myDialog.setCancelable(true);
				myDialog.show();
			} else {
				habit = new Habit(goal.getText().toString(), 
								  start_date.getText().toString(),
								  duration.getText().toString(),
								  spinner_difficulty.getSelectedItem().toString(),
								  spinner_difficulty.getSelectedItemPosition(),
								  spinner_questionary_frequency.getSelectedItem().toString(),
								  spinner_questionary_frequency.getSelectedItemPosition(),
								  null, user);
				setContentView(R.layout.activity_questionary);
			}	
		} else {
			setContentView(R.layout.activity_questionary);
		}
	}
	
	/** Called when the user clicks the Right Arrow button */
	public void back(View view) {
	    // Do something in response to button
		
		setContentView(R.layout.activity_customize_habit);
		
		if(checkBack) {
			EditText goal = (EditText) findViewById(R.id.goal);
			EditText start_date = (EditText) findViewById(R.id.start_date);
			EditText duration = (EditText) findViewById(R.id.duration);
			Spinner spinner_difficulty = (Spinner) findViewById(R.id.spinner_difficulty);
			Spinner spinner_questionary_frequency = (Spinner) findViewById(R.id.spinner_questionary_frequency);
			
			goal.setText(habit.getGoal());
			start_date.setText(habit.getStart_date());
			duration.setText(habit.getDuration());
			spinner_difficulty.setSelection(habit.getDifficultyPosition());
			spinner_questionary_frequency.setSelection(habit.getFrequencyPosition());
		}

	}
	
	/** Called when the user clicks the Check Mark button */
	public void createQuestionary(View view) {
				
		ArrayList<Question> questions = new ArrayList<Question>();
		EditText questionEditText = null;
		Question q = null;
		Answer a = null;
		int radioButtonIdentifier = 0;
		
		for(int i = 0; i < fillQuestions.size(); ++i) {
			
			questionEditText = (EditText) findViewById(hashViewID.get("question_" + fillQuestions.get(i)));
			
			RadioGroup radioGroup = (RadioGroup) findViewById(hashViewID.get("question_radio_layout_" + fillQuestions.get(i)));
			
			radioButtonIdentifier = radioGroup.getCheckedRadioButtonId();
			
			if(radioButtonIdentifier == -1) {
				
			}
			
			RadioButton radioButton = (RadioButton) findViewById(radioButtonIdentifier);
			
			if(radioButton.getText().toString().equals("Numeric")) {

				Spinner ContributionSpinner = (Spinner) findViewById(hashViewID.get("contribution_" + fillQuestions.get(i)));
				EditText contributionValueEditText = (EditText) findViewById(hashViewID.get("contribution_value_" + fillQuestions.get(i)));
				EditText contributionValueRankEditText = (EditText) findViewById(hashViewID.get("contribution_value_rank" + fillQuestions.get(i)));
				
				q = new NumericQuestion(questionEditText.getText().toString(), 
										((String) ContributionSpinner.getSelectedItem()),
										contributionValueEditText.getText().toString(),
										contributionValueRankEditText.getText().toString());
				
			} else if (radioButton.getText().toString().equals("Selection")) {
				
				ArrayList<Integer> answerArray = fillAnswers.get(fillQuestions.get(i));
				ArrayList<Answer> answers = new ArrayList<Answer>();
				
				EditText answerEditText = null;
				EditText scoreEditText = null;
				
				for(int j = 0; j < answerArray.size(); ++j) {
					
					answerEditText = (EditText) findViewById(hashViewID.get("answer_" + fillQuestions.get(i) + "_" + answerArray.get(j)));
					scoreEditText  = (EditText) findViewById(hashViewID.get("score_"  + fillQuestions.get(i) + "_" + answerArray.get(j)));
					
					a = new Answer(answerEditText.getText().toString(), scoreEditText.getText().toString());
					answers.add(a);
				}
				
				q = new ChoiceQuestion(questionEditText.getText().toString(), answers);
				
			}
			questions.add(q);

		}
		habit.setQuestions(questions);
		Database db = new Database();
		db.saveHabit(habit);
		Session.lastHabit = habit;
		Intent intent = new Intent(this, Home.class);
		startActivity(intent);
		
		/*
		Dialog myDialog = new Dialog(this);
		TextView textError = new  TextView(this);
		textError.setText(habit.toString().replace(":", "\n"));
		myDialog.setContentView(textError);
		myDialog.show();
		*/
			
		/*
	    // Do something in response to button
		if(part1.length()!=0 && ans.length()!=0){
			String[] parts1 = part1.split(" : ");
			String[] subPart1 = parts1[0].split("\t");
			String[] subPart4 = parts1[3].split("\t");
			String[] subPart5 = parts1[4].split("\t");
			String[] partsAns = ans.split(" : ");
			Bundle extras = getIntent().getExtras();
			if( parts1.length==5 && partsAns.length>=1 &&
					subPart4[1].compareTo("Select Questionary Frequency")!=0 &&
					subPart5[1].compareTo("Select Difficulty")!=0){
				String text = checkGoal(extras.getString("user"), subPart1[1]);
				if(text!=null){
					try{
						File dir = getFilesDir();
						File file = new File(dir, extras.getString("user"));
						boolean deleted = file.delete();
						FileOutputStream fOut = openFileOutput(extras.getString("user"), MODE_APPEND);
						String line = part1 +"\n";
						fOut.write(text.getBytes());
						fOut.write(line.getBytes());
						Intent intent = new Intent(this, Home.class);
						startActivity(intent);
					}catch (IOException ioe) {
						ioe.printStackTrace ( ) ;
					}
				}else{
					Dialog myDialog = new Dialog(this);
					TextView textError = new  TextView(this);
					textError.setGravity(1);
					textError.setText("These goal already exists");
					myDialog.setContentView(textError);
					myDialog.setCancelable(true);
					myDialog.show();
				}
			}
		}
		Dialog myDialog = new Dialog(this);
		TextView textError = new  TextView(this);
		textError.setText("Please complety the empty fields and be sure that you have at least one question");
		myDialog.setContentView(textError);
		myDialog.setTitle("Error");
		myDialog.setCancelable(true);
		myDialog.show();*/
		
	}
	
	private String checkGoal (String username, String goal) {
        try {
            FileInputStream fIn = openFileInput ( username ) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String text="";
            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
            	String[] data = readString.split(" : ");
            	String[] data1 = data[0].split("\t"); 
                if(data1[1].compareTo(goal)==0){
                	return null;
                }
                text = text + readString +"\n";
                readString = buffreader.readLine ( ) ;
            }
            isr.close ( ) ;
            return text;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        return null;
    }
	
	/** Called when the user clicks the Add Question button */
	public void addQuestion(View view) {
		
		LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.main_questionary_linear_layout);
		LinearLayout questionLinearLayout = new LinearLayout(mainLinearLayout.getContext());
		assignID("question_layout_" + question);
		questionLinearLayout.setId(hashViewID.get("question_layout_" + question));
		questionLinearLayout.setOrientation(1); // Vertical
		
		// New linear layout for a question
		LinearLayout linearLayout = new LinearLayout(questionLinearLayout.getContext());
		
		// Question text
		assignID("question_" + question);
		EditText editText = new EditText(linearLayout.getContext());
		editText.setId(hashViewID.get("question_" + question));
		editText.setHint(R.string.fill_question);
		editText.requestFocus();
		LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1.0f);
		editText.setLayoutParams(params);
		
		// Delete question button
		Button button = new Button (linearLayout.getContext(), null, android.R.attr.buttonStyleSmall);
		button.setText(R.string.delete);
		button.setBackgroundResource(R.drawable.delete_question_button_shape);
		button.setTextColor(getResources().getColor(android.R.color.white));
		DeleteQuestionButton deleteQuestionButton = new DeleteQuestionButton(question);
		button.setOnClickListener(deleteQuestionButton);
		
		linearLayout.addView(editText);
		linearLayout.addView(button);

		// Radio buttons
		LinearLayout radioLinearLayout = new LinearLayout(mainLinearLayout.getContext());
		
		RadioGroup radioGroup = new RadioGroup(radioLinearLayout.getContext());
		assignID("question_radio_layout_" + question);
		radioGroup.setId(hashViewID.get("question_radio_layout_" + question));
		
		TextView textView = new TextView(radioLinearLayout.getContext(), null, android.R.attr.textAppearanceMedium);
		textView.setText(R.string.select_kind);
		
		// Kind Linear Layout
		LinearLayout linearLayoutKind = new LinearLayout(mainLinearLayout.getContext());
		assignID("kind_layout_" + question);
		linearLayoutKind.setId(hashViewID.get("kind_layout_" + question));
		linearLayoutKind.setOrientation(1);
		
		// Numeric radio button
		RadioButton radioButtonNumeric = new RadioButton(radioGroup.getContext());
		assignID("radio_button_numeric_" + question);
		radioButtonNumeric.setId(hashViewID.get("radio_button_numeric_" + question));
		radioButtonNumeric.setText(R.string.numeric);
		NumericRadioButtonListener numericRadioButtonListener = new NumericRadioButtonListener(question);
		radioButtonNumeric.setOnClickListener(numericRadioButtonListener);
		//radioButtonNumeric.setChecked(true);
		
		// Choice radio button
		RadioButton radioButtonChoice = new RadioButton(radioGroup.getContext());
		assignID("radio_button_choice_" + question);
		radioButtonChoice.setId(hashViewID.get("radio_button_choice_" + question));
		radioButtonChoice.setText(R.string.selection);
		ChoiceRadioButtonListener choiceRadioButtonListener = new ChoiceRadioButtonListener(question);
		radioButtonChoice.setOnClickListener(choiceRadioButtonListener);
		
		radioGroup.addView(radioButtonNumeric);
		radioGroup.addView(radioButtonChoice);
		
		radioLinearLayout.addView(textView);
		radioLinearLayout.addView(radioGroup);
		
		questionLinearLayout.addView(linearLayout);
		questionLinearLayout.addView(radioLinearLayout);
		questionLinearLayout.addView(linearLayoutKind);
		
		mainLinearLayout.addView(questionLinearLayout);
		
		fillQuestions.add(question);
		
		++question;
	}
	
	private void assignID(String key) {
		hashViewID.put(key, viewID);
		++viewID;
	}
	
	public class DeleteQuestionButton implements Button.OnClickListener {
		
		private int question;
		
		public DeleteQuestionButton() {

		}

		public DeleteQuestionButton(int question) {
			super();
			this.question = question;
		}

		@Override
		public void onClick(View arg0) {
			LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.main_questionary_linear_layout);
			LinearLayout questionLinearLayout = (LinearLayout) findViewById(hashViewID.get("question_layout_" + question));
			mainLinearLayout.removeView(questionLinearLayout);
			fillQuestions.remove(question);
		}

		public int getQuestion() {
			return question;
		}

		public void setQuestion(int question) {
			this.question = question;
		}
		
	}
	
	public class NumericRadioButtonListener implements RadioButton.OnClickListener {
		
		private int question;

		public NumericRadioButtonListener() {

		}
		
		public NumericRadioButtonListener(int question) {
			super();
			this.question = question;
		}

		@Override
		public void onClick(View v) {
			
			((RadioButton) findViewById(hashViewID.get("radio_button_choice_" + question))).setChecked(false);
			((RadioButton) v).setChecked(true);
			
			LinearLayout linearLayoutKind = (LinearLayout) findViewById(hashViewID.get("kind_layout_" + question));
			linearLayoutKind.removeAllViews();
			
			Spinner spinner = new Spinner(linearLayoutKind.getContext());
			assignID("contribution_" + question);
			spinner.setId(hashViewID.get("contribution_" + question));
			spinner.setPrompt(getString(R.string.select_kind));
			// Create an ArrayAdapter using the string array and a default spinner layout
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CustomizeHabit.this,
			        R.array.contribution_array, android.R.layout.simple_spinner_item);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			spinner.setAdapter(adapter);
			
			EditText editTextContribution = new EditText(linearLayoutKind.getContext());
			assignID("contribution_value_" + question);
			editTextContribution.setId(hashViewID.get("contribution_value_" + question));
			LayoutParams paramsContribution = new LinearLayout.LayoutParams(
	                LayoutParams.MATCH_PARENT,
	                LayoutParams.WRAP_CONTENT);
			editTextContribution.setHint(R.string.contribution_value);
			editTextContribution.setLayoutParams(paramsContribution);
			editTextContribution.setInputType(InputType.TYPE_CLASS_NUMBER);
			
			EditText editTextContributionRank = new EditText(linearLayoutKind.getContext());
			assignID("contribution_value_rank" + question);
			editTextContributionRank.setId(hashViewID.get("contribution_value_rank" + question));
			paramsContribution = new LinearLayout.LayoutParams(
	                LayoutParams.MATCH_PARENT,
	                LayoutParams.WRAP_CONTENT);
			editTextContributionRank.setHint(R.string.contribution_value_rank);
			editTextContributionRank.setLayoutParams(paramsContribution);
			editTextContributionRank.setInputType(InputType.TYPE_CLASS_NUMBER);
				
			linearLayoutKind.addView(spinner);
			linearLayoutKind.addView(editTextContribution);
			linearLayoutKind.addView(editTextContributionRank);
		}

		public int getQuestion() {
			return question;
		}

		public void setQuestion(int question) {
			this.question = question;
		}
		
	}
	
	public class ChoiceRadioButtonListener implements RadioButton.OnClickListener {

		private int question;

		public ChoiceRadioButtonListener() {

		}
		
		public ChoiceRadioButtonListener(int question) {
			super();
			this.question = question;
		}
		
		@Override
		public void onClick(View v) {
			
			((RadioButton) findViewById(hashViewID.get("radio_button_numeric_" + question))).setChecked(false);
			((RadioButton) v).setChecked(true);
			
			LinearLayout linearLayoutKind = (LinearLayout) findViewById(hashViewID.get("kind_layout_" + question));
			linearLayoutKind.removeAllViews();
			
			assignID("main_answer_layout_" + this.question);
			
			//ScrollView answerScrollView = new ScrollView(linearLayoutKind.getContext());
			//LayoutParams paramsScrollViewAnswer = new LinearLayout.LayoutParams(LayoutParams. MATCH_PARENT, 100);
			//answerScrollView.setLayoutParams(paramsScrollViewAnswer);
			
			LinearLayout mainAnswerLinearLayout = new LinearLayout(linearLayoutKind.getContext());
			mainAnswerLinearLayout.setId(hashViewID.get("main_answer_layout_" + question));
			LayoutParams paramslinearLayoutAnswer = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT,
	                LayoutParams.WRAP_CONTENT);
			mainAnswerLinearLayout.setLayoutParams(paramslinearLayoutAnswer);
			mainAnswerLinearLayout.setOrientation(1);
			//answerScrollView.addView(mainAnswerLinearLayout);
			
			Button addAnswerbutton = new Button (linearLayoutKind.getContext(), null, android.R.attr.buttonStyleSmall);
			addAnswerbutton.setText(R.string.add_answer);
			AddAnswerButtonListener addAnswerButtonListener = new AddAnswerButtonListener(question);
			addAnswerbutton.setOnClickListener(addAnswerButtonListener);
			addAnswerbutton.setTextColor(getResources().getColor(android.R.color.white));
			addAnswerbutton.setBackgroundResource(R.drawable.add_question_button_shape);
			
			linearLayoutKind.addView(mainAnswerLinearLayout);
			linearLayoutKind.addView(addAnswerbutton);
			
			fillAnswers.put(question, new ArrayList<Integer>());
		}
		
		public int getQuestion() {
			return question;
		}

		public void setQuestion(int question) {
			this.question = question;
		}
		
	}
	
	public class AddAnswerButtonListener implements Button.OnClickListener {

		private int question;
		private int answer;

		public AddAnswerButtonListener() {

		}
		
		public AddAnswerButtonListener(int question) {
			super();
			this.question = question;
		}
		
		@Override
		public void onClick(View v) {
			
			LinearLayout mainAnswerLinearLayout = (LinearLayout) findViewById(hashViewID.get("main_answer_layout_" + question));
			LinearLayout answerLinearLayout = new LinearLayout(mainAnswerLinearLayout.getContext());
			assignID("answer_layout_" + question + "_" + answer);
			answerLinearLayout.setId(hashViewID.get("answer_layout_" + question + "_" + answer));
			
			// Answer
			EditText editTextAnswer = new EditText(answerLinearLayout.getContext());
			assignID("answer_" + question + "_" + answer);
			editTextAnswer.setId(hashViewID.get("answer_" + question + "_" + answer));
			LayoutParams paramsAnswer = new LinearLayout.LayoutParams(
	                150,
	                LayoutParams.WRAP_CONTENT);
			editTextAnswer.setHint(R.string.answer);
			editTextAnswer.setLayoutParams(paramsAnswer);
			editTextAnswer.setInputType(InputType.TYPE_CLASS_TEXT);
			
			// Score
			EditText editTextScore = new EditText(answerLinearLayout.getContext());
			assignID("score_" + question + "_" + answer);
			editTextScore.setId(hashViewID.get("score_" + question + "_" + answer));
			LayoutParams paramsScore = new LinearLayout.LayoutParams(
					80,
	                LayoutParams.WRAP_CONTENT);
			editTextScore.setHint(R.string.score);
			editTextScore.setLayoutParams(paramsScore);
			editTextScore.setInputType(InputType.TYPE_CLASS_NUMBER);
			
			// Delete Answer Button
			Button deleteAnswerButton = new Button (answerLinearLayout.getContext(), null, android.R.attr.buttonStyleSmall);
			deleteAnswerButton.setText(R.string.delete_answer);
			deleteAnswerButton.setBackgroundResource(R.drawable.delete_question_button_shape);
			deleteAnswerButton.setTextColor(getResources().getColor(android.R.color.white));
			
			DeleteAnswerButtonListener deleteAnswerButtonListener = new DeleteAnswerButtonListener(question, answer);
			deleteAnswerButton.setOnClickListener(deleteAnswerButtonListener);
			
			answerLinearLayout.addView(editTextAnswer);
			answerLinearLayout.addView(editTextScore);
			answerLinearLayout.addView(deleteAnswerButton);
			
			mainAnswerLinearLayout.addView(answerLinearLayout);
			
			fillAnswers.get(question).add(answer);
			
			++answer;
			
		}
		
		public int getQuestion() {
			return question;
		}

		public void setQuestion(int question) {
			this.question = question;
		}
	
	}
	
	public class DeleteAnswerButtonListener implements Button.OnClickListener {
		
		private int question;
		private int answer;
		
		public DeleteAnswerButtonListener() {

		}

		public DeleteAnswerButtonListener(int question, int answer) {
			super();
			this.question = question;
			this.answer = answer;
		}

		@Override
		public void onClick(View v) {
			LinearLayout mainAnswerLinearLayout = (LinearLayout) findViewById(hashViewID.get("main_answer_layout_" + question));
			LinearLayout answerLinearLayout = (LinearLayout) findViewById(hashViewID.get("answer_layout_" + question + "_" + answer));
			mainAnswerLinearLayout.removeView(answerLinearLayout);
			
			fillAnswers.get(question).remove(answer);
		}

		public int getQuestion() {
			return question;
		}

		public void setQuestion(int question) {
			this.question = question;
		}

		public int getAnswer() {
			return answer;
		}

		public void setAnswer(int answer) {
			this.answer = answer;
		}
	
	}

}
