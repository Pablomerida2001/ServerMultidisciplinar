package Models;

public class MovementRequest {
	private String movement;
	private String date;
	
	public MovementRequest(String movement, String date) {
		super();
		this.movement = movement;
		this.date = date;
	}
	public MovementRequest() {
		super();
	}
	public String getMovement() {
		return movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
