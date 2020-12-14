package Server;

public class User {
	
	int id;
	String name;
	String surname;
	Role role;
	
	public User(int id, String name, String surname, Role role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}