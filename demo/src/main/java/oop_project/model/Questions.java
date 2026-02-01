package oop_project.model;

import java.util.Objects;

public class Questions {
    private int number;
    private String text;
    private String correctAnswer;
    private int points;

    public Questions(int number, String text, String correctAnswer, int points){
        if(number <= 0) throw new IllegalArgumentException("Question number must be equal or bigger than 0!");
        if(text == null || text.isBlank()) throw new IllegalArgumentException("Question text is required!");
        if(correctAnswer == null || correctAnswer.isBlank())throw new IllegalArgumentException("Answer is required!");
        if(points <= 0) throw new IllegalArgumentException("Points must be equal or bigger than 0!");

        this.number = number;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }
    public int getNumber(){
        return number;
    }
    public String getText(){
        return text;
    }
    public int getPoints(){
        return points;
    }
    public int grade(Answer answer){
        if(answer == null) return 0;
        String ans = normalize(answer.getText());
        String correct = normalize(correctAnswer);

        return Objects.equals(ans, correct) ? points : 0;
    }
    public String normalize(String text){
        return text.trim().toLowerCase();
    }
}
