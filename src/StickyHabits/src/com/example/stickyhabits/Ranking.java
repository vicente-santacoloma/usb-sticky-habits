package com.example.stickyhabits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Ranking extends Activity {
	
	private String ranking [] = new String [] {"Iker Casillas", "Raul Albiol", "Xavi Hernandez", "Xabi Alonso", 
											   "Jesus Navas", "Fernando Torres", "Fernando Llorente", "David Villa",
											   "Andres Iniesta", "Cesc Fabregas", "Jordi Alba", "Alvaro Arveloa", 
											   "Santi Cazorla", "Pepe Reina", "Sergio Busquets", "Juan Mata", 
											   "Victor Valdes", "Pedro Rodriguez", "Gerard Pique", "You"};
	
	private int score [] = new int [] {5904, 5540, 5200, 5130, 4323, 3029, 2670, 2567, 2450, 2023, 1893, 1532, 1245, 1234, 1123, 1025, 998, 876, 456, 245};

	protected void onCreate(Bundle savedInstanceState) {
	  
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_ranking);
		ListView listView = (ListView) findViewById(R.id.ranking);
	    ArrayList<Map<String, String>> list = buildData();
	    String[] from = { "username", "score"};
	    int[] to = { android.R.id.text1, android.R.id.text2};
	
	    SpecialAdapter adapter = new SpecialAdapter(this, list,
	        R.layout.ranking_row_layout, from, to);
	    listView.setAdapter(adapter);
	}
	
	private ArrayList<Map<String, String>> buildData() {
	  
	    ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    for(int i = 0; i < ranking.length; ++i) {
	    	list.add(putData((i + 1) + ". " + ranking[i], "Score: " + score[i]));
	    }
	    return list;
	}

	private HashMap<String, String> putData(String username, String score) {
		  
	    HashMap<String, String> item = new HashMap<String, String>();
	    item.put("username", username);
	    item.put("score", score);
	    
	    return item;
	}
	
	public class SpecialAdapter extends SimpleAdapter {
		
	    private int[] colors = new int[] { 0xFF00A1D9, 0xFFF2F2F2 };
	     
	    public SpecialAdapter(Context context, ArrayList<Map<String, String>> items, int resource, String[] from, int[] to) {
	        super(context, items, resource, from, to);
	    }
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	
	    	View view = super.getView(position, convertView, parent);
	    	//TextView textView = (TextView) view.findViewById(R.id.label);
	        //ImageView imageView = (ImageView) view.findViewById(R.id.icon); 
	    	
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
	
	  
}

/*
public class Ranking extends Activity {

	private String ranking [] = new String [] {"Iker Casillas", "Raul Albiol", "Xavi Hernandez", "Xabi Alonso", 
			   								   "Jesus Navas", "Fernando Torres", "Fernando Llorente", "David Villa",
			   								   "Andres Iniesta", "Cesc Fabregas", "Jordi Alba", "Alvaro Arveloa", 
			   								   "Santi Cazorla", "Pepe Reina", "Sergio Busquets", "Juan Mata", 
			   								   "Victor Valdes", "Pedro Rodriguez", "Gerard Pique", "You"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		loadRanking();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ranking, menu);
		return true;
	}

	private void loadRanking() {
		
		LinearLayout rankingLinearLayout = (LinearLayout) findViewById(R.id.RankingLinearLayout);
		
		for(int i = 0; i < ranking.length; ++i) {
			//LinearLayout linearLayout = new LinearLayout(rankingLinearLayout.getContext());
			TextView textView = new TextView(rankingLinearLayout.getContext(), null, android.R.attr.textAppearanceLarge);
			textView.setText((i + 1) + " " + ranking[i]);
			LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.MATCH_PARENT,
	                LayoutParams.WRAP_CONTENT, 1.0f);
			textView.setLayoutParams(params);
			
			if(i % 2 == 0) {
				textView.setBackgroundResource(R.drawable.element_even_shape);
			} else {
				textView.setBackgroundResource(R.drawable.element_odd_shape);
			}
			//linearLayout.addView(textView);
			rankingLinearLayout.addView(textView);
		}
	}

}*/
