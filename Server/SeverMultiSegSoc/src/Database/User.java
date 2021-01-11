package Database;

import java.io.Serializable;

public class User implements Serializable{
	
	private int Id = -1;
	private String Name;
	private String Surname;
	private String Role;
	private String Email;
	private String Password;
	private Boolean OnlineStatus;
	
	public User(int Id, String Name, String Surname, String Role, String Email, String Password, Boolean Online) {
		this.Id = Id;
		this.Name = Name;
		this.Surname = Surname;
		this.Role = Role;
		this.Email = Email;
		this.Password = Password;
		this.OnlineStatus = Online;
	}
	
	public User() {}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	public Boolean getOnline() {
		return OnlineStatus;
	}

	public void setOnline(Boolean online) {
		OnlineStatus = online;
	}

}