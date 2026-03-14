package oop_project.model;

import java.util.LinkedHashMap;

public class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(int number, String text, String correctAnswer, int points) {
        super(number, text, correctAnswer, points);
        if (!isValidAnswer(correctAnswer)) {
            throw new IllegalArgumentException("Correct answer must be a or b!");
        }
    }

    @Override
    public LinkedHashMap<String, String> getChoices() {
        LinkedHashMap<String, String> choices = new LinkedHashMap<>();
        choices.put("a", "True");
        choices.put("b", "False");
        return choices;
    }

    @Override
    public String getAnswerPrompt() {
        return "Your answer (a/b): ";
    }

    @Override
    public boolean isValidAnswer(String input) {
        String normalized = normalize(input);
        return normalized.equals("a") || normalized.equals("b");
    }

    @Override
    public String getTypeLabel() {
        return "True/False";
    }
}
