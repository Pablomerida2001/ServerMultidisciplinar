package Models;
/*
 * Clase ContentModel
 * 
 * Modelo para el content
 *  
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.Serializable;

public class ContentModel implements Serializable{

    private String text;

    /*
     * Constructor
     */
    public ContentModel(String text){
        this.text = text;
    }

    /*
     * Getters y Setters
     */
    public void setText(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}