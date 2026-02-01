package oop_project.app;

import oop_project.model.*;
import java.util.Scanner;

public class Main 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);

        Exam exam = SampleData.createSampleExam();
        Attempt attempt = new Attempt(exam);

        System.out.println("=== " + exam.getTitle() + " ===");
        System.out.println("Questions: " + exam.getQuestions().size());
        System.out.println();

        for (Questions q : exam.getQuestions()) {
            System.out.println("Q" + q.getNumber() + ": " + q.getText());
            System.out.print("Your answer: ");
            String input = sc.nextLine();

            // Encapsulation: Attempt controls how answers are stored
            attempt.submitAnswer(q.getNumber(), Answer.fromText(input));
            System.out.println();
        }

        // Encapsulation: Exam/Attempt own the rules for scoring
        int score = attempt.calculateScore();
        System.out.println("=== RESULT ===");
        System.out.println("Score: " + score + " / " + exam.getTotalPoints());

        sc.close();
    }
}