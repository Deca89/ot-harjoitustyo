/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.kemiantestaaja.domain.CourseSelections;
import fi.kemiantestaaja.domain.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juuri
 */
public class DatabaseTest {

    Database database;

    @Before
    public void setUp() {
        CourseSelections d = new CourseSelections();
        d.deleteCourse("test2");
        d.addCourse("test2");
        database = new Database("test2");
        this.database.addTerm("test1", "first term");
    }

    @Test
    public void canAddToDatabase() {
        boolean blob = this.database.removeTerm("test11");
        boolean added = this.database.addTerm("test11", "test111");

        assertTrue(added);
    }

    @Test
    public void cantAddSameTermTwiceToDatabase() {
        boolean blob = this.database.addTerm("test11", "test111");
        boolean added = this.database.addTerm("test11", "test111");

        assertTrue(!added);
    }

    @Test
    public void canRemoveFromDatabase() throws SQLException {
        this.database.addTerm("test11", "test111");
        boolean removed = this.database.removeTerm("test11");

        assertTrue(removed);
    }

    @Test
    public void cantRemoveGhostsFromDatabase() throws SQLException {
        boolean blob = this.database.removeTerm("test11");
        boolean removed = this.database.removeTerm("test11");

        assertTrue(!removed);
    }

    @Test
    public void termsInDatabaseCorrect() throws SQLException {
        this.database.removeTerm("test11");
        int first = this.database.numberOfTerms();

        assertTrue(first==1);
    }
    
    @Test
    public void canGetTerms() {
        ArrayList<String> terms = this.database.getTerms();  
        assertTrue(terms.get(0).equalsIgnoreCase("test1"));
    }
    
    @Test
    public void canGetTermsAndExplanations() {
        ArrayList<String> termsAndExplanations = this.database.getTermsAndExplanations();
        assertTrue(termsAndExplanations.get(0).equalsIgnoreCase("test1") && termsAndExplanations.get(1).equalsIgnoreCase("first term"));
    }
 
   
}
