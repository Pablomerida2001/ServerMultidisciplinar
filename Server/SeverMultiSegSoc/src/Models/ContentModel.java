package Models;

public class ContentModel {

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