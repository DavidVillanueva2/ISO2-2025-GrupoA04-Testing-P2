package es.uclm.iso2.becas;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Command-line entry point. Only reads input and prints output; all the
 * business logic lives in {@link ScholarshipEvaluator}.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.ROOT); // decimal point: 8.5
        try {
            System.out.print("GPA (0-10): ");
            double gpa = in.nextDouble();
            System.out.print("Family income (EUR): ");
            double income = in.nextDouble();
            System.out.print("Distance to campus (km): ");
            double distance = in.nextDouble();
            System.out.print("Disability degree (%): ");
            int disability = in.nextInt();
            System.out.print("Number of siblings: ");
            int siblings = in.nextInt();

            Student student = new Student(gpa, income, distance, disability, siblings);
            ScholarshipType result = new ScholarshipEvaluator().evaluate(student);
            System.out.println("Result: " + result.getDisplayName() + " (" + result.getBenefits() + ")");
        } catch (InvalidStudentDataException e) {
            System.out.println("Invalid data: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: please enter numbers only");
        }
    }
}
