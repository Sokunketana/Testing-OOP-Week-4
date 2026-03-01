package oop_project.app;

import oop_project.model.*;
import oop_project.data.SampleData;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserRegistry registry = new UserRegistry();
        registry.registerUser("student1", "1234", "student");
        registry.registerUser("teacher1", "pass", "teacher");

        Exam currentExam = SampleData.createSampleExam();

        boolean appRunning = true;
        while (appRunning) {
            System.out.println("\n=== Login ===");
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            if (!registry.validateLogin(username, password)) {
                System.out.println("Invalid login.");
            } else {
                User loggedInUser = registry.getUser(username);
                System.out.println("Welcome, " + loggedInUser.getUsername() + " (" + loggedInUser.getRole() + ")");

                if (loggedInUser instanceof Teacher) {
                    Exam published = runTeacherMenu(sc, (Teacher) loggedInUser);
                    if (published != null) {
                        currentExam = published;
                        System.out.println("Exam is now available for students.");
                    }
                } else if (loggedInUser instanceof Student) {
                    runStudentMenu(sc, currentExam);
                }
            }

            System.out.print("\nLogin again? (y/n): ");
            String again = sc.nextLine().trim().toLowerCase();
            if (!again.equals("y")) {
                appRunning = false;
            }
        }

        System.out.println("Goodbye!");
        sc.close();
    }

    private static Exam runTeacherMenu(Scanner sc, Teacher teacher) {
        Exam published = null;
        boolean running = true;
        while (running) {
            System.out.println("\n=== Teacher Menu ===");
            System.out.println("1. Add question");
            System.out.println("2. View draft questions");
            System.out.println("3. Publish exam");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Question text: ");
                    String text = sc.nextLine();

                    LinkedHashMap<String, String> choices = new LinkedHashMap<>();
                    System.out.print("Choice A: ");
                    choices.put("a", sc.nextLine());
                    System.out.print("Choice B: ");
                    choices.put("b", sc.nextLine());
                    System.out.print("Choice C: ");
                    choices.put("c", sc.nextLine());
                    System.out.print("Choice D: ");
                    choices.put("d", sc.nextLine());

                    String correct = "";
                    while (!correct.equals("a") && !correct.equals("b")
                            && !correct.equals("c") && !correct.equals("d")) {
                        System.out.print("Correct answer (a/b/c/d): ");
                        correct = sc.nextLine().trim().toLowerCase();
                    }

                    int questionNumber = teacher.getDraftQuestions().size() + 1;
                    Question q = new Question(questionNumber, text, choices, correct, 1);
                    teacher.addDraftQuestion(q);
                    System.out.println("Question " + questionNumber + " added.");
                    break;

                case "2":
                    List<Question> drafts = teacher.getDraftQuestions();
                    if (drafts.isEmpty()) {
                        System.out.println("No questions added yet.");
                    } else {
                        System.out.println("\n=== Draft Questions ===");
                        for (Question dq : drafts) {
                            System.out.println("Q" + dq.getNumber() + ": " + dq.getText());
                            for (Map.Entry<String, String> entry : dq.getChoices().entrySet()) {
                                System.out.println("  " + entry.getKey() + ") " + entry.getValue());
                            }
                        }
                    }
                    break;

                case "3":
                    if (teacher.getDraftQuestions().isEmpty()) {
                        System.out.println("Add at least one question before publishing.");
                    } else {
                        System.out.print("Exam title: ");
                        String title = sc.nextLine();
                        published = teacher.buildExam(title);
                        System.out.println("Exam \"" + published.getTitle() + "\" published!");
                        running = false;
                    }
                    break;

                case "4":
                    running = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        return published;
    }

    private static void runStudentMenu(Scanner sc, Exam exam) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Take exam");
            System.out.println("2. Logout");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    Map<Integer, Answer> answers = new HashMap<>();
                    System.out.println("\n=== " + exam.getTitle() + " ===");

                    for (Question q : exam.getQuestions()) {
                        System.out.println("\nQ" + q.getNumber() + ": " + q.getText());
                        for (Map.Entry<String, String> entry : q.getChoices().entrySet()) {
                            System.out.println("  " + entry.getKey() + ") " + entry.getValue());
                        }
                        System.out.print("Your answer (a/b/c/d): ");
                        String input = sc.nextLine().trim().toLowerCase();
                        answers.put(q.getNumber(), Answer.fromText(input));
                    }

                    int score = 0;
                    for (Question q : exam.getQuestions()) {
                        score += q.grade(answers.get(q.getNumber()));
                    }

                    System.out.println("\n=== RESULT ===");
                    System.out.println("Score: " + score + " / " + exam.getTotalPoints());
                    break;

                case "2":
                    running = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
