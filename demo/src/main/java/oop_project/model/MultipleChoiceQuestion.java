package oop_project.model;

import java.util.LinkedHashMap;

public class MultipleChoiceQuestion extends Question {
    private final LinkedHashMap<String, String> choices;

    public MultipleChoiceQuestion(int number, String text, LinkedHashMap<String, String> choices, String correctAnswer, int points) {
        super(number, text, correctAnswer, points);
        if (choices == null || choices.size() != 4) {
            throw new IllegalArgumentException("Question must have exactly 4 choices!");
        }
        if (!choices.containsKey("a") || !choices.containsKey("b")
                || !choices.containsKey("c") || !choices.containsKey("d")) {
            throw new IllegalArgumentException("Choices must use keys a, b, c, and d!");
        }
        if (!isValidAnswer(correctAnswer)) {
            throw new IllegalArgumentException("Correct answer must be one of a, b, c, or d!");
        }

        this.choices = new LinkedHashMap<>(choices);
    }

    @Override
    public LinkedHashMap<String, String> getChoices() {
        return new LinkedHashMap<>(choices);
    }

    @Override
    public String getAnswerPrompt() {
        return "Your answer (a/b/c/d): ";
    }

    @Override
    public boolean isValidAnswer(String input) {
        String normalized = normalize(input);
        return normalized.equals("a") || normalized.equals("b")
                || normalized.equals("c") || normalized.equals("d");
    }

    @Override
    public String getTypeLabel() {
        return "Multiple Choice";
    }
}
