package oop_project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Teacher extends User {
    private final List<Question> draftQuestions;
    private final List<Exam> managedExams;

    public Teacher(String username, String password) {
        super(username, password);
        this.draftQuestions = new ArrayList<>();
        this.managedExams = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "teacher";
    }

    public void addDraftQuestion(Question question) {
        draftQuestions.add(question);
    }

    public List<Question> getDraftQuestions() {
        return Collections.unmodifiableList(draftQuestions);
    }

    public Exam buildExam(String title) {
        return buildExam(title, "General");
    }

    public Exam buildExam(String title, String category) {
        if (draftQuestions.isEmpty()) {
            throw new IllegalStateException("Cannot publish an exam with no questions.");
        }
        Exam exam = new Exam(title, category, draftQuestions);
        managedExams.add(exam);
        draftQuestions.clear();
        return exam;
    }

    public List<Exam> getManagedExams() {
        return Collections.unmodifiableList(managedExams);
    }
}
