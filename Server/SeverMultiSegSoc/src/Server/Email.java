package Server;

public class Email {
	
	User user;
	String email;
	String password;
	
	public Email(User user, String email, String password) {
		this.user = user;
		this.email = email;
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
}