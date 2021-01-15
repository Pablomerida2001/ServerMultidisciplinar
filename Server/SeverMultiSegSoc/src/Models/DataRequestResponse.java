package Models;
/*
 * Clase DataRequestResponse
 * 
 * Modelo que se uteliza para comunicacion con el servidor
 *  
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.Serializable;
import java.util.ArrayList;

public class DataRequestResponse implements Serializable{

	private String action;
	private String error;
	private String errorMessage;
	private ArrayList<Object> data = new ArrayList<Object>();
	
	/**
     * Constructor con 4 parametros
     * @param action - type String - accion
     * @param error - type String - muestra su hay error
     * @param errorMessage - type String - mensage de error
     * @param data - type ArrayList<Object> -  datos
     */
	public DataRequestResponse(String action, String error, String errorMessage, ArrayList<Object> data) {
		super();
		this.action = action;
		this.error = error;
		this.errorMessage = errorMessage;
	}
	
	/**
     * Constructor por defecto
     */
	public DataRequestResponse() {
		super();
	}

	/*
	 * Getters y Setters
	 */
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
