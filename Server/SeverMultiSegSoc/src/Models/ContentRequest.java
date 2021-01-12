package Models;

import java.io.Serializable;

public class ContentRequest implements Serializable{
	
	private int index;
	private String lenguage;
	
	public ContentRequest(int index, String lenguage) {
		this.index = index;
		this.lenguage = lenguage;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getLenguage() {
		return lenguage;
	}
	public void setLenguage(String lenguage) {
		this.lenguage = lenguage;
	}
}