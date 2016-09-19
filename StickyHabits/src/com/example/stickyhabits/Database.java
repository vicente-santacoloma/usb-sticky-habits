package com.example.stickyhabits;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;

public class Database {

	private String userFile  = "users.db";
	private String habitFile = "habits.db";
	private static Context context;

	public boolean getUser(User user) {
		
		File file = new File(context.getFilesDir(), userFile);
		String [] data = null;
	    Scanner scanner = null;
	    
	    try {
	      scanner = new Scanner(file);
	    } catch (FileNotFoundException ex) {
	      return false; //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    String line;
	    do {
	    	line = scanner.nextLine();
	    	data = line.split(":");
	    	
	    	if(data[0].equals(user.getUsername())) {
	    		user.setEmail(data[1]);
	    		user.setPassword(data[2]);
	    		return true;
	    	}
	    	
	    } while(scanner.hasNextLine());
	   		
		return false;
	}
	
	public boolean getUsers (ArrayList<User> users) {
		
		File file = new File(context.getFilesDir(), userFile);
		String [] data = null;
	    Scanner scanner = null;
	    User user = null;
	    
	    if(users == null) {
	    	users = new ArrayList<User>();
	    }
	    
	    try {
	      scanner = new Scanner(file);
	    } catch (FileNotFoundException ex) {
	      return false; //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    String line;
	    do {
	    	line = scanner.nextLine();
	    	data = line.split(":");
	    		
    		user = new User (data[0], data[1], data[2]);

	    	users.add(user);
	    	
	    } while(scanner.hasNextLine());
	   		
		return true;

	}
	
	public boolean saveUser(User user) {
		
		FileWriter fileWriter = null;
		String content = user.toString() + "\n";
        try {
        	File file = new File(context.getFilesDir(), userFile);
            fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException ex) {
          //Logger.getLogger(WriteStringToFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
            	fileWriter.close();
            } catch (IOException ex) {
                //Logger.getLogger(WriteStringToFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
	}
	
	public boolean saveUsers(ArrayList<User> users) {
		
		FileWriter fileWriter = null;
		String content = "";
		for(int i = 0; i < users.size(); ++i) {		
			content += users.get(i).toString() + "\n";
		}
		
        try {
        	File file = new File(context.getFilesDir(), userFile);
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException ex) {
          //Logger.getLogger(WriteStringToFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
            	fileWriter.close();
            } catch (IOException ex) {
                //Logger.getLogger(WriteStringToFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
	}

	public boolean getHabit (Habit habit) {
		
		File file = new File(context.getFilesDir(), habitFile);
		String [] data = null;
	    Scanner scanner = null;
	    Question question = null;
	    Answer answer = null;
	    ArrayList<Question> questions = null;
	    ArrayList<Answer> answers = null;
	    
	    try {
	      scanner = new Scanner(file);
	    } catch (FileNotFoundException ex) {
	      return false; //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    String line;
	    do {
	    	line = scanner.nextLine();
	    	data = line.split(":");
	    	
	    	if(data[0].equals(habit.getUser().getUsername()) && data[1].equals(habit.getGoal())) {
	    		
	    		habit.setTotalScore(Integer.parseInt(data[2]));
	    		habit.setStart_date(data[3]);
	    		habit.setDuration(data[4]);
	    		habit.setFrequency(data[5]);
	    		habit.setDifficulty(data[6]);
	    		int questionNumber = Integer.parseInt(data[7]);
	    		questions = new ArrayList<Question>();
	    		habit.setQuestions(questions);
	    		int k = 8;
	    		for(int i = 0; i < questionNumber; ++i) {
	    			
	    			if(data[k].equals("numeric")) {
	    				question = new NumericQuestion(data[k + 1], 
	    											   data[k + 2], 
	    											   data[k + 3],
	    											   data[k + 4]);
	    				k += 5;	
	    			} else if(data[k].equals("choice")) {
	    				answers = new ArrayList<Answer>();
	    				question = new ChoiceQuestion(data[k + 1], answers);
	    				int answerNumber = Integer.parseInt(data[k + 2]);
	    				
	    				k += 3;

	    				for(int j = 0; j < answerNumber; ++j) {
	    					answer = new Answer(data[k],
	    									    data[k + 1]);
	    					answers.add(answer);
	    					k += 2;
	    				}
	    				((ChoiceQuestion) question).setAnswers(answers);
	    				
	    			}
	    			questions.add(question);
	    		}
	    		return true;
	    	}
	    	
	    } while(scanner.hasNextLine());
	   		
		return false;
	}
	
	public boolean getHabits (ArrayList<Habit> habits, User user) {
		
		File file = new File(context.getFilesDir(), habitFile);
		String [] data = null;
	    Scanner scanner = null;
	    Question question = null;
	    Answer answer = null;
	    ArrayList<Question> questions = null;
	    ArrayList<Answer> answers = null;
	    Habit habit = null;
	    boolean getUsers = false;
	    boolean checkUsers = false;
	    
    	if(user == null) {
    		getUsers = true;
    	} else {
    		checkUsers = true;
    	}
	    
	    if(habits == null) {
	    	habits = new ArrayList<Habit>();
	    }
	    
	    try {
	      scanner = new Scanner(file);
	    } catch (FileNotFoundException ex) {
	      return false; //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    String line;
	    do {
	    	line = scanner.nextLine();
	    	data = line.split(":");
	    		
	    	if(getUsers || (checkUsers && data[0].equals(user.getUsername()))) {
	    		habit = new Habit (data[1], data[3], data[4], data[5], data[6], questions, user);
	    		habit.setTotalScore(Integer.parseInt(data[2]));
	
	    		int questionNumber = Integer.parseInt(data[7]);
	    		questions = new ArrayList<Question>();
	    		habit.setQuestions(questions);
	    		int k = 8;
	    		for(int i = 0; i < questionNumber; ++i) {
	    			
	    			if(data[k].equals("numeric")) {
	    				question = new NumericQuestion(data[k + 1], 
	    											   data[k + 2], 
	    											   data[k + 3],
	    											   data[k + 4]);
	    				k += 5;	
	    			} else if(data[k].equals("choice")) {
	    				answers = new ArrayList<Answer>();
	    				question = new ChoiceQuestion(data[k + 1], answers);
	    				int answerNumber = Integer.parseInt(data[k + 2]);
	    				
	    				k += 3;
	
	    				for(int j = 0; j < answerNumber; ++j) {
	    					answer = new Answer(data[k],
	    									    data[k + 1]);
	    					answers.add(answer);
	    					k += 2;
	    				}
	    				((ChoiceQuestion) question).setAnswers(answers);
	    				
	    			}
	    			questions.add(question);
	    		}
		    	habits.add(habit);
	    	}
	    	
	    } while(scanner.hasNextLine());
	   		
		return true;

	}
	
	public boolean saveHabit(Habit habit) {
		
		FileWriter fileWriter = null;
		String content = habit.toString() + "\n";
		
        try {
        	File file = new File(context.getFilesDir(), habitFile);
            fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException ex) {
          //Logger.getLogger(WriteStringToFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
            	fileWriter.close();
            } catch (IOException ex) {
                //Logger.getLogger(WriteStringToFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

	}
	
	public boolean saveHabits(ArrayList<Habit> habits) {
		
		FileWriter fileWriter = null;
		String content = "";
		
		for(int i = 0; i < habits.size(); ++i) {
			content += habits.toString() + "\n";
		}
		
        try {
        	File file = new File(context.getFilesDir(), habitFile);
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException ex) {
          //Logger.getLogger(WriteStringToFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
            	fileWriter.close();
            } catch (IOException ex) {
                //Logger.getLogger(WriteStringToFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		Database.context = context;
	}
	
}
