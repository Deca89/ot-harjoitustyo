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
        CourseSelections c = new CourseSelections();
        c.addCourse("test2");
        database = new Database("test2");
        
    }
  
    @Test
    public void canAddToDatabase() throws SQLException {
        int terms = this.database.termsInDatabase();
        
        this.database.addTerm("test11", "test111");
        
        int terms2 = this.database.termsInDatabase();
        
        assertTrue(terms2>terms);
    }
    
    @Test
    public void canRemoveFromDatabase() throws SQLException {
        this.database.addTerm("test11", "test111");
        int terms = this.database.termsInDatabase();
        
        this.database.removeTerm("test11");
        
        int terms2 = this.database.termsInDatabase();
        
        assertTrue(terms2<terms);
    }
    
    

}