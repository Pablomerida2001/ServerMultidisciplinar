package Models;

import java.io.Serializable;

public class ContentModel implements Serializable{

    private String text;

    public ContentModel(String text){
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}