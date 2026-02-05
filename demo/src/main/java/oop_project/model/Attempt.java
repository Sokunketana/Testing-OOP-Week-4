package oop_project.model;

import java.util.HashMap;
import java.util.Map;

public class Attempt {
    private Exam exam; 
    private Map<Integer, Answer> answers;

    public Attempt(Exam exam) { 
        if (exam == null) throw new IllegalArgumentException("Exam is required");
        this.exam = exam;
        this.answers = new HashMap<>();
    }

    // Encapsulation: Attempt controls how answers are stored/updated
    public void submitAnswer(int questionNumber, Answer answer) {
        if (questionNumber <= 0) throw new IllegalArgumentException("Invalid question number");
        answers.put(questionNumber, answer);
    }

    public Answer getAnswer(int questionNumber) {
        return answers.get(questionNumber);
    }

    // Encapsulation: scoring logic is not in Main
    public int calculateScore() {
        int total = 0;

        // Changed Question to Questions to match Questions.java
        for (Question q : exam.getQuestions()) { 
            Answer a = answers.get(q.getNumber());
            total += q.grade(a);
        }

        return total;
    }
}