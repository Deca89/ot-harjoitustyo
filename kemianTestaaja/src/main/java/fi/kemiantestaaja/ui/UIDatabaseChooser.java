/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.domain.CourseSelections;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Juuri
 */
public class UIDatabaseChooser {

    private CourseSelections courses;
    private Scanner scanner;

    public UIDatabaseChooser() {
        this.courses = new CourseSelections();
        this.scanner = new Scanner(System.in);
    }

    public void start() throws SQLException, ClassNotFoundException, IOException {
        System.out.println("Tervetuloa harjoitustyön maailmaan\n");
        printOptions();

        while (true) {

            System.out.println("Valitse toiminto (1-4, valikko, lopeta)");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                addCourse();
            } else if (input.equals("2")) {
                chooseCourse();
            } else if (input.equals("3")) {
                this.courses.listCourses();
                System.out.println("");
            } else if (input.equals("4")) {
                deleteCourse();
            } else if (input.equals("valikko")) {
                printOptions();
            } else if (input.equals("lopeta")) {
                return;
            }

        }
    }

    public void printOptions() {
        System.out.println("\nValikko:");
        System.out.println("1: Luo kurssi");
        System.out.println("2: Valitse olemassa oleva kurssi");
        System.out.println("3: Listaa olemassa olevat kurssit");
        System.out.println("4: Poista kurssi");
        System.out.println("valikko: Tulosta valikko\n");
    }

    public void addCourse() throws SQLException, ClassNotFoundException {
        System.out.print("\nNimeä kurssi: ");
        String course = scanner.nextLine();
        this.courses.addCourse(course);
        startCourse(course);
    }

    public void chooseCourse() throws ClassNotFoundException, SQLException {
        System.out.print("\nValitse kurssi: ");
        String course = scanner.nextLine();
        startCourse(course);
    }

    public void deleteCourse() throws ClassNotFoundException {
        System.out.print("\nValitse poistettava kurssi: ");
        String course = scanner.nextLine();
        this.courses.deleteCourse(course);
    }

    public void startCourse(String course) throws ClassNotFoundException, SQLException {
        boolean courseExists = this.courses.doesCourseExist(course);
        if (courseExists) {
            UserInterface begin = new UserInterface(course);
            begin.start();
        }
    }

}
