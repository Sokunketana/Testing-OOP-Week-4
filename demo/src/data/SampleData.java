package data;

import model.Exam;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public class SampleData {

    public static Exam createSampleExam() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                1,
                "What keyword is used to create a subclass in Java?",
                "extends",
                2
        ));

        questions.add(new Question(
                2,
                "Which access modifier hides fields to support encapsulation?",
                "private",
                2
        ));

        questions.add(new Question(
                3,
                "What method name do we commonly use to read a private field? (one word)",
                "getter",
                2
        ));

        return new Exam("Week 1 - Encapsulation Only Quiz", questions);
    }

}
}