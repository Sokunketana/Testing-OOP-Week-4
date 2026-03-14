package oop_project.model;

import java.util.LinkedHashMap;
import java.util.Objects;

public abstract class Question {
    private final int number;
    private final String text;
    private final String correctAnswer;
    private final int points;

    protected Question(int number, String text, String correctAnswer, int points) {
        if (number <= 0) throw new IllegalArgumentException("Question number must be equal or bigger than 0!");
        if (text == null || text.isBlank()) throw new IllegalArgumentException("Question text is required!");
        if (correctAnswer == null || correctAnswer.isBlank()) throw new IllegalArgumentException("Answer is required!");
        if (points <= 0) throw new IllegalArgumentException("Points must be equal or bigger than 0!");

        this.number = number;
        this.text = text;
        this.correctAnswer = normalize(correctAnswer);
        this.points = points;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public abstract LinkedHashMap<String, String> getChoices();

    public abstract String getAnswerPrompt();

    public abstract boolean isValidAnswer(String input);

    public abstract String getTypeLabel();

    public int grade(Answer answer) {
        if (answer == null) return 0;
        String ans = normalize(answer.getText());
        return Objects.equals(ans, correctAnswer) ? points : 0;
    }

    protected String normalize(String text) {
        return text == null ? "" : text.trim().toLowerCase();
    }
}
