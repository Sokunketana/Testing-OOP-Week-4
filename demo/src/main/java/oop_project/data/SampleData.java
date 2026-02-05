package oop_project.data;

import oop_project.model.Exam;
import oop_project.model.Question;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static Exam createSampleExam() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                1,
                "Answer is a",
                "a",
                2
        ));

        questions.add(new Question(
                2,
                "Answer is a",
                "a",
                2
        ));

        questions.add(new Question(
                3,
                "Answer is a",
                "a",
                2
        ));

        return new Exam("Test", questions);
    }

}
