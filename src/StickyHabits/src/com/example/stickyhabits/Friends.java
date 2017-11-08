package com.example.stickyhabits;

import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Friends extends Activity {

	private String friends [] = new String [] {"Iker Casillas", "Raul Albiol", "Xavi Hernandez", "Xabi Alonso", 
											   "Jesus Navas", "Fernando Torres", "Fernando Llorente", "David Villa",
											   "Andres Iniesta", "Cesc Fabregas", "Jordi Alba", "Alvaro Arveloa", 
											   "Santi Cazorla", "Pepe Reina", "Sergio Busquets", "Juan Mata", 
											   "Victor Valdes", "Pedro Rodriguez", "Gerard Pique"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		ListView listView = (ListView) findViewById(R.id.my_friends);
		SpecialAdapter adapter = new SpecialAdapter(this,
				R.layout.friends_row_layout, R.id.friend, friends);
		listView.setAdapter(adapter);

		//setContentView(R.layout.activity_friends);
		//loadFriends();
	}
	
	public class SpecialAdapter extends ArrayAdapter<String> {
		
	    private int[] colors = new int[] { 0xFF00A1D9, 0xFFF2F2F2 };
	    private final Context context;
	    private final String [] friends; 
	    
	    
	    public SpecialAdapter(Activity context, int resource,int textViewResourceId, String [] friends) {
	        super(context, resource, textViewResourceId, friends);
	        this.context = context;
	        this.friends = friends;
	    }
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	
	    	View view = super.getView(position, convertView, parent);
	    	//TextView textView = (TextView) view.findViewById(R.id.label);
	        //ImageView imageView = (ImageView) view.findViewById(R.id.icon); 
	    	
	    	Button button = (Button) view
	    	          .findViewById(R.id.unfriend);
	    	button.setBackgroundResource(R.drawable.unfriend_button_shape);
	    	button.setOnClickListener(new DeleteFriend());
	    	
	    	if(position % 2 == 0) {
	    		view.setBackgroundResource(R.drawable.element_even_shape);
	    	} else {
	    		view.setBackgroundResource(R.drawable.element_odd_shape);
	    	}
			/*
			int colorPos = position % colors.length;
			view.setBackgroundColor(colors[colorPos]);
			*/
			return view;
	    }
	}
/*
	private void loadFriends() {
		
		LinearLayout friendsLinearLayout = (LinearLayout) findViewById(R.id.FriendsLinearLayout);
		
		for(int i = 0; i < friends.length; ++i) {
			LinearLayout linearLayout = new LinearLayout(friendsLinearLayout.getContext());
			TextView textView = new TextView(linearLayout.getContext(), null, android.R.attr.textAppearanceMedium);
			textView.setText(friends[i]);
			LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.MATCH_PARENT,
	                LayoutParams.WRAP_CONTENT, 1.0f);
			textView.setLayoutParams(params);
			//textView.setBackgroundResource(R.drawable.element_shape);

			Button button = new Button (linearLayout.getContext(), null, android.R.attr.buttonStyleSmall);
			button.setText(R.string.unfriend);
			if(i % 2 == 0) {
				linearLayout.setBackgroundResource(R.drawable.element_even_shape);
			} else {
				linearLayout.setBackgroundResource(R.drawable.element_odd_shape);
			}
			button.setBackgroundResource(R.drawable.unfriend_button_shape);
			
			linearLayout.addView(textView);
			linearLayout.addView(button);
			friendsLinearLayout.addView(linearLayout);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_friends, menu);
		return true;
	}*/
	
	public class DeleteFriend implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			
			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
			builder.setMessage("Are you sure?");
			builder.setCancelable(true);
			builder.setPositiveButton("Accept", null);
			builder.setNegativeButton("Cancel", null);
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	
	}

}
