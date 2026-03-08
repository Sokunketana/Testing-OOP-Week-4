package oop_project.data;

import oop_project.model.Exam;
import oop_project.model.Question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SampleData {
    public static List<Exam> createSampleExams() {
        List<Exam> exams = new ArrayList<>();

        List<Question> mathQuestions = new ArrayList<>();
        LinkedHashMap<String, String> mathChoices1 = new LinkedHashMap<>();
        mathChoices1.put("a", "6");
        mathChoices1.put("b", "7");
        mathChoices1.put("c", "8");
        mathChoices1.put("d", "9");
        mathQuestions.add(new Question(1, "What is 3 + 4?", mathChoices1, "b", 1));

        LinkedHashMap<String, String> mathChoices2 = new LinkedHashMap<>();
        mathChoices2.put("a", "12");
        mathChoices2.put("b", "15");
        mathChoices2.put("c", "18");
        mathChoices2.put("d", "20");
        mathQuestions.add(new Question(2, "What is 3 x 5?", mathChoices2, "b", 1));
        exams.add(new Exam("Math Basics", "Math", mathQuestions));

        List<Question> geographyQuestions = new ArrayList<>();
        LinkedHashMap<String, String> geographyChoices1 = new LinkedHashMap<>();
        geographyChoices1.put("a", "Paris");
        geographyChoices1.put("b", "London");
        geographyChoices1.put("c", "Berlin");
        geographyChoices1.put("d", "Rome");
        geographyQuestions.add(new Question(1, "What is the capital of France?", geographyChoices1, "a", 1));

        LinkedHashMap<String, String> geographyChoices2 = new LinkedHashMap<>();
        geographyChoices2.put("a", "Asia");
        geographyChoices2.put("b", "Africa");
        geographyChoices2.put("c", "Europe");
        geographyChoices2.put("d", "Australia");
        geographyQuestions.add(new Question(2, "Which continent is Cambodia in?", geographyChoices2, "a", 1));
        exams.add(new Exam("Geography Quick Quiz", "Geography", geographyQuestions));

        List<Question> scienceQuestions = new ArrayList<>();
        LinkedHashMap<String, String> scienceChoices1 = new LinkedHashMap<>();
        scienceChoices1.put("a", "7");
        scienceChoices1.put("b", "8");
        scienceChoices1.put("c", "9");
        scienceChoices1.put("d", "10");
        scienceQuestions.add(new Question(1, "How many planets are in the solar system?", scienceChoices1, "b", 1));

        LinkedHashMap<String, String> scienceChoices2 = new LinkedHashMap<>();
        scienceChoices2.put("a", "Hydrogen");
        scienceChoices2.put("b", "Oxygen");
        scienceChoices2.put("c", "Nitrogen");
        scienceChoices2.put("d", "Carbon Dioxide");
        scienceQuestions.add(new Question(2, "What gas do humans need to breathe?", scienceChoices2, "b", 1));
        exams.add(new Exam("Science Basics", "Science", scienceQuestions));

        return exams;
    }
}
