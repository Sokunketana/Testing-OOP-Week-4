package oop_project.app;

import oop_project.model.*;
import oop_project.data.SampleData;
import java.util.ArrayList;
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

        List<Exam> availableExams = new ArrayList<>(SampleData.createSampleExams());

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
                        availableExams.add(published);
                        System.out.println("Exam is now available for students.");
                    }
                } else if (loggedInUser instanceof Student) {
                    runStudentMenu(sc, availableExams);
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
                    int questionNumber = teacher.getDraftQuestions().size() + 1;
                    Question q = createQuestion(sc, questionNumber);
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
                            System.out.println("Q" + dq.getNumber() + " [" + dq.getTypeLabel() + "]: " + dq.getText());
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

                        String category = "";
                        while (category.isBlank()) {
                            System.out.print("Exam category: ");
                            category = sc.nextLine().trim();
                            if (category.isBlank()) {
                                System.out.println("Category cannot be empty.");
                            }
                        }

                        published = teacher.buildExam(title, category);
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

    private static void runStudentMenu(Scanner sc, List<Exam> availableExams) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Take exam");
            System.out.println("2. Logout");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    if (availableExams.isEmpty()) {
                        System.out.println("No exams available yet.");
                        break;
                    }

                    Exam selectedExam = selectExam(sc, availableExams);
                    if (selectedExam == null) {
                        break;
                    }

                    Map<Integer, Answer> answers = new HashMap<>();
                    System.out.println("\n=== " + selectedExam.getTitle() + " (" + selectedExam.getCategory() + ") ===");

                    for (Question q : selectedExam.getQuestions()) {
                        System.out.println("\nQ" + q.getNumber() + ": " + q.getText());
                        for (Map.Entry<String, String> entry : q.getChoices().entrySet()) {
                            System.out.println("  " + entry.getKey() + ") " + entry.getValue());
                        }
                        answers.put(q.getNumber(), readAnswerForQuestion(sc, q));
                    }

                    int score = 0;
                    for (Question q : selectedExam.getQuestions()) {
                        score += q.grade(answers.get(q.getNumber()));
                    }

                    System.out.println("\n=== RESULT ===");
                    System.out.println("Score: " + score + " / " + selectedExam.getTotalPoints());
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

    private static Exam selectExam(Scanner sc, List<Exam> availableExams) {
        LinkedHashMap<String, String> categories = new LinkedHashMap<>();
        for (Exam exam : availableExams) {
            String normalized = exam.getCategory().trim().toLowerCase();
            categories.putIfAbsent(normalized, exam.getCategory());
        }

        List<String> categoryKeys = new ArrayList<>(categories.keySet());

        while (true) {
            System.out.println("\nChoose a category:");
            for (int i = 0; i < categoryKeys.size(); i++) {
                String label = categories.get(categoryKeys.get(i));
                System.out.println((i + 1) + ". " + label);
            }
            System.out.println("0. Back");

            int categoryChoice = readMenuNumber(sc, "Choice: ", categoryKeys.size());
            if (categoryChoice == 0) {
                return null;
            }

            String selectedCategoryKey = categoryKeys.get(categoryChoice - 1);
            List<Exam> examsInCategory = new ArrayList<>();
            for (Exam exam : availableExams) {
                if (exam.getCategory().trim().toLowerCase().equals(selectedCategoryKey)) {
                    examsInCategory.add(exam);
                }
            }

            while (true) {
                System.out.println("\nChoose an exam:");
                for (int i = 0; i < examsInCategory.size(); i++) {
                    System.out.println((i + 1) + ". " + examsInCategory.get(i).getTitle());
                }
                System.out.println("0. Back");

                int examChoice = readMenuNumber(sc, "Choice: ", examsInCategory.size());
                if (examChoice == 0) {
                    break;
                }

                return examsInCategory.get(examChoice - 1);
            }
        }
    }

    private static int readMenuNumber(Scanner sc, String prompt, int maxOption) {
        while (true) {
            System.out.print(prompt);
            String raw = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(raw);
                if (value >= 0 && value <= maxOption) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Keep asking until valid.
            }

            System.out.println("Invalid choice.");
        }
    }

    private static Question createQuestion(Scanner sc, int questionNumber) {
        System.out.println("Question type:");
        System.out.println("1. Multiple choice");
        System.out.println("2. True/False");
        int questionType = readMenuNumber(sc, "Choice: ", 2);

        String text = readRequiredText(sc, "Question text: ");

        if (questionType == 1) {
            LinkedHashMap<String, String> choices = new LinkedHashMap<>();
            System.out.print("Choice A: ");
            choices.put("a", sc.nextLine());
            System.out.print("Choice B: ");
            choices.put("b", sc.nextLine());
            System.out.print("Choice C: ");
            choices.put("c", sc.nextLine());
            System.out.print("Choice D: ");
            choices.put("d", sc.nextLine());

            String correct = readMultipleChoiceAnswer(sc, "Correct answer (a/b/c/d): ");
            return new MultipleChoiceQuestion(questionNumber, text, choices, correct, 1);
        }

        String correct = readTrueFalseAnswer(sc, "a=true, b=false. Correct answer (a/b): ");
        return new TrueFalseQuestion(questionNumber, text, correct, 1);
    }

    private static Answer readAnswerForQuestion(Scanner sc, Question question) {
        while (true) {
            System.out.print(question.getAnswerPrompt());
            String input = sc.nextLine().trim().toLowerCase();
            if (question.isValidAnswer(input)) {
                return Answer.fromText(input);
            }
            System.out.println("Invalid answer.");
        }
    }

    private static String readRequiredText(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (!value.isBlank()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static String readMultipleChoiceAnswer(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("a") || answer.equals("b") || answer.equals("c") || answer.equals("d")) {
                return answer;
            }
            System.out.println("Invalid answer.");
        }
    }

    private static String readTrueFalseAnswer(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("a") || answer.equals("b")) {
                return answer;
            }
            System.out.println("Invalid answer.");
        }
    }
}
