/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.kemiantestaaja.domain.CourseSelections;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juuri
 */
public class CourseSelectionsTest {

    CourseSelections c;

    @Before
    public void setUp() {
        c = new CourseSelections();
        c.addCourse("test111");
    }

    @Test
    public void canAddCourse() {
        c.addCourse("test11");
        File f = new File("test11.db");
        assertTrue(f.exists());
    }

    @Test
    public void canOnlyAddCourseOnce() {
        c.addCourse("test11");
        int canAddAgain = c.addCourse("test11");
        assertTrue(canAddAgain == 1);
    }

    @Test
    public void canDeleteCourse() {
        File f = new File("test111.db");
        c.deleteCourse("test111");

        assertTrue(!f.exists());
    }

    @Test
    public void canCheckIfCourseExists() {
        c.addCourse("test11");

        Boolean checkWorks = c.doesCourseExist("test11");

        assertTrue(checkWorks);
    }

    @Test
    public void canListCoursesAccurately() throws IOException {
        List<String> first = c.listCourses();

        Stream<Path> curDirFiles = Files.walk(Paths.get("."));

        List<String> courses = curDirFiles.map(x -> x.toString())
                .filter(f -> f.endsWith(".db")).collect(Collectors.toList());

        assertTrue(first.size() == courses.size());
    }

}
