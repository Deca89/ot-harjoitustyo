/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.kemiantestaaja.logics.CourseSelections;
import java.io.File;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    }

    @Test
    public void canAddCourse() throws SQLException, ClassNotFoundException {

        c.addCourse("test11");
        File f = new File("test11.db");
        assertTrue(f.exists());

    }
    
    @Test
    public void canDeleteCourse() throws SQLException, ClassNotFoundException {
        c.addCourse("test11");
        c.deleteCourse("test11");
        
        File f = new File("test11.db");
        assertTrue(!f.exists());
    }
}
