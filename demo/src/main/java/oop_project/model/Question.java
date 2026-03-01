package oop_project.model;

import java.util.LinkedHashMap;
import java.util.Objects;

public class Question {
    private int number;
    private String text;
    private LinkedHashMap<String, String> choices;
    private String correctAnswer;
    private int points;

    public Question(int number, String text, LinkedHashMap<String, String> choices, String correctAnswer, int points) {
        if (number <= 0) throw new IllegalArgumentException("Question number must be equal or bigger than 0!");
        if (text == null || text.isBlank()) throw new IllegalArgumentException("Question text is required!");
        if (choices == null || choices.size() != 4) throw new IllegalArgumentException("Question must have exactly 4 choices!");
        if (correctAnswer == null || correctAnswer.isBlank()) throw new IllegalArgumentException("Answer is required!");
        if (points <= 0) throw new IllegalArgumentException("Points must be equal or bigger than 0!");

        this.number = number;
        this.text = text;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public LinkedHashMap<String, String> getChoices() {
        return choices;
    }

    public int getPoints() {
        return points;
    }

    public int grade(Answer answer) {
        if (answer == null) return 0;
        String ans = normalize(answer.getText());
        String correct = normalize(correctAnswer);
        return Objects.equals(ans, correct) ? points : 0;
    }

    public String normalize(String text) {
        return text.trim().toLowerCase();
    }
}
