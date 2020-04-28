/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.kemiantestaaja.logics.CourseSelections;
import fi.kemiantestaaja.logics.Database;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class DatabaseTest {
    
    Database database;
    
    @Before
    public void setUp() throws SQLException, ClassNotFoundException{
        CourseSelections d = new CourseSelections();
        d.deleteCourse("test2");
        d.addCourse("test2");
        database = new Database("test2");
        
    }
  
    @Test
    public void canAddToDatabase() throws SQLException {
        Integer blob = this.database.removeTerm("test11");
        Integer added = this.database.addTerm("test11", "test111");
        
        assertTrue(added == 1);
    }
    
    @Test
    public void cantAddSameTermTwiceToDatabase() throws SQLException {
        Integer blob = this.database.addTerm("test11", "test111");
        Integer added = this.database.addTerm("test11", "test111");
        
        assertTrue(added == 1);
    }
    
    
    
//    @Test
//    public void canRemoveFromDatabase() throws SQLException {
//        this.database.addTerm("test11", "test111");
//        int removed = this.database.removeTerm("test11");
//        
//        assertTrue(removed==2);
//    }
//    
//    @Test
//    public void cantRemoveGhostsFromDatabase() throws SQLException {
//        int blob = this.database.removeTerm("test11");
//        int removed = this.database.removeTerm("test11");
//        
//        assertTrue(removed==1);
//    }
    
//    @Test
//    public void termsInDatabaseCorrect() throws SQLException {
//        this.database.removeTerm("test11");
//        int first = this.database.termsInDatabase();
//        this.database.addTerm("test11", "jotain");
//        int second = this.database.termsInDatabase();
//        
//        assertTrue(second == (first + 1));
//    }
//    
//    @Test
//    public void getTermsCorrectNumbers() throws SQLException {
//        ArrayList<String> first = this.database.getTerms();
//        this.database.addTerm("Mimi", "Söpöin koira");
//        ArrayList<String> second = this.database.getTerms();
//        
//        assertTrue(second.size() == (first.size() + 1));
//    }
//    
//    @Test
//    public void getTermsAndExplanationsCorrectNumbers() throws SQLException {
//        ArrayList<String> first = this.database.printTermsAndExplanations();
//        this.database.addTerm("Mimi", "Söpöin koira");
//        ArrayList<String> second = this.database.printTermsAndExplanations();
//        
//        assertTrue(second.size() == (first.size() + 3));
//    }
//    
//    
//    
//    

}