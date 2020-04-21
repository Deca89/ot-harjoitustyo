/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.logics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Juuri
 */
public class CourseSelections {
    
    public CourseSelections() {
        
    }
    
    public void addCourse(String course) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String check = course + ".db";
        File file = new File(check);

        if (file.exists()) {
            System.out.println("Kurssi on jo olemassa\n");
        } else {
            Connection db = DriverManager.getConnection("jdbc:sqlite:" + course + ".db");
            System.out.println("Kurssi luotu\n");

            db.createStatement().execute("CREATE TABLE IF NOT EXISTS Terms(id INTEGER PRIMARY KEY, name TEXT UNIQUE NOT NULL)");
            db.createStatement().execute("CREATE TABLE IF NOT EXISTS Explanations(Term_id INTEGER REFERENCES Terms ON DELETE CASCADE, explanation TEXT UNIQUE NOT NULL)");
        }
    }
    
    public void listCourses() throws IOException {
        Stream<Path> curDirFiles = Files.walk(Paths.get("."));
        
        List<String> courses = curDirFiles.map(x -> x.toString())
                .filter(f -> f.endsWith(".db")).collect(Collectors.toList());
        
        courses.forEach(System.out::println);
    }
    
    public void deleteCourse(String course) throws ClassNotFoundException {
        if (doesCourseExist(course)) {
            String check = course + ".db";
            File file = new File(check);
            file.delete();
            System.out.println("Kurssi " + course + " poistettu.");
        } else {
            System.out.println("Kurssia " + course + "ei ole olemassa.");
        }
    }
    
    public boolean doesCourseExist(String course) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String check = course + ".db";
        File file = new File(check);
        
        if (!file.exists()) {
            System.out.println("\nKurssia ei ole vielä olemassa. \nYstävällisesti luo ensin kurssi ja yritä sitten uudestaan.\n");
        }
        return file.exists();
    }
}
