package oop_project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exams {
    private String title;
    private List<Questions> questions;

    public Exams(String title, List<Questions> questions) {
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

    public List<Questions> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public int getTotalPoints() {
        int total = 0;
        for (Questions q : questions)
            total += q.getPoints();
        return total;
    }

    public Questions findQuestionByNumber(int number) {
        for (Questions q : questions) {
            if (q.getNumber() == number)
                return q;
        }
        return null;
    }
}
