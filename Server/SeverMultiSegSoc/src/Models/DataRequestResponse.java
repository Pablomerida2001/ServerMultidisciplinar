package Models;

import java.util.ArrayList;

public class DataRequestResponse {

	private String action;
	private String error;
	private String errorMessage;
	private ArrayList<Object> data = new ArrayList<Object>();
	
	public DataRequestResponse(String action, String error, String errorMessage, ArrayList<Object> data) {
		super();
		this.action = action;
		this.error = error;
		this.errorMessage = errorMessage;
		this.data = data;
	}
	

	public DataRequestResponse() {
		super();
	}


	public void addData(Object obj) {
		this.data.add(obj);
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ArrayList<Object> getData() {
		return data;
	}

	public void setData(ArrayList<Object> data) {
		this.data = data;
	}
	
	
}
