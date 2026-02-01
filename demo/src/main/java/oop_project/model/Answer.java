package oop_project.model;

public class Answer {
    private String text;
    private Answer(String text){
        this.text = (text == null) ? "" : text;
    }
    public static Answer fromText(String text){
        return new Answer(text);
    }
    public String getText(){
        return text;
    }
}
