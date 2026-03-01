package oop_project.data;

import oop_project.model.Exam;
import oop_project.model.Question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SampleData {
    public static Exam createSampleExam() {
        List<Question> questions = new ArrayList<>();

        LinkedHashMap<String, String> choices1 = new LinkedHashMap<>();
        choices1.put("a", "Monday");
        choices1.put("b", "Tuesday");
        choices1.put("c", "Wednesday");
        choices1.put("d", "Thursday");
        questions.add(new Question(1, "What day comes after Monday?", choices1, "b", 1));

        LinkedHashMap<String, String> choices2 = new LinkedHashMap<>();
        choices2.put("a", "Paris");
        choices2.put("b", "London");
        choices2.put("c", "Berlin");
        choices2.put("d", "Rome");
        questions.add(new Question(2, "What is the capital of France?", choices2, "a", 1));

        LinkedHashMap<String, String> choices3 = new LinkedHashMap<>();
        choices3.put("a", "8");
        choices3.put("b", "9");
        choices3.put("c", "10");
        choices3.put("d", "12");
        questions.add(new Question(3, "How many planets are in the solar system?", choices3, "b", 1));

        return new Exam("Sample Quiz", questions);
    }
}
