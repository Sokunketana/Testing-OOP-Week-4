package oop_project.model;

import java.util.HashMap;
import java.util.Map;

public class Attempt {
    private final Exam exam;
    private final Map<Integer, Answer> answers;

    public Attempt(Exam exam) {
        if (exam == null) throw new IllegalArgumentException("Exam is required");
        this.exam = exam;
        this.answers = new HashMap<>();
    }

    public void submitAnswer(int questionNumber, Answer answer) {
        if (questionNumber <= 0) throw new IllegalArgumentException("Invalid question number");
        answers.put(questionNumber, answer);
    }

    public Answer getAnswer(int questionNumber) {
        return answers.get(questionNumber);
    }

    public Exam getExam() {
        return exam;
    }

    public int calculateScore() {
        int total = 0;
        for (Question q : exam.getQuestions()) {
            Answer a = answers.get(q.getNumber());
            total += q.grade(a);
        }
        return total;
    }
}
