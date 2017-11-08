package com.example.stickyhabits;

public class User {
	
	private String username;
	private String email;
	private String password;
	private String repeat_password;
	
	public User() {
		super();
	}
	
	public User(String username) {
		super();
		this.username = username;
	}

	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(String username, String email, String password,
			String repeat_password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.repeat_password = repeat_password;
	}

	public String toString() {
		
		return username + ":" + email + ":" + password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeat_password() {
		return repeat_password;
	}

	public void setRepeat_password(String repeat_password) {
		this.repeat_password = repeat_password;
	}
	
}
