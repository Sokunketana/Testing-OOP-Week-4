package oop_project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exam {
    private String title;
    private String category;
    private List<Question> questions;

    public Exam(String title, List<Question> questions) {
        this(title, "General", questions);
    }

    public Exam(String title, String category, List<Question> questions) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title is required");
        if (category == null || category.isBlank())
            throw new IllegalArgumentException("Category is required");
        if (questions == null || questions.isEmpty())
            throw new IllegalArgumentException("Questions are required");

        this.title = title;
        this.category = category.trim();
        this.questions = new ArrayList<>(questions);
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
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
