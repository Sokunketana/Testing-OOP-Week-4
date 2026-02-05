package oop_project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exam {
    private String title;
    private List<Question> questions;

    public Exam(String title, List<Question> questions) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title is required");
        if (questions == null || questions.isEmpty())
            throw new IllegalArgumentException("Questions are required");

        this.title = title;
        this.questions = new ArrayList<>(questions);
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public int getTotalPoints() {
        int total = 0;
        for (Question q : questions)
            total += q.getPoints();
        return total;
    }

    public Question findQuestionByNumber(int number) {
        for (Question q : questions) {
            if (q.getNumber() == number)
                return q;
        }
        return null;
    }
}
