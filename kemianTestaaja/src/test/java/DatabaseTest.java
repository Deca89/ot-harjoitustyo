/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.kemiantestaaja.logics.Database;
import java.io.File;
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
    public void setUp() throws SQLException, ClassNotFoundException {
        database = new Database();
    }
    
    
    
    @Test
    public void doesDatabaseExistWorksWithDatabase() {
        File file = new File("chemistryTester.db");
        assertEquals(file.exists(), database.doesDatabaseExist());
    }
    
//    @Test
//    public void canCreateDatabase() {
//        
//    }
//    
    @Test
    public void canAddToDatabase() throws SQLException {
        PreparedStatement p1 = DriverManager.getConnection("jdbc:sqlite:chemistryTester.db").prepareStatement("SELECT COUNT(*) FROM Terms");
        ResultSet r = p1.executeQuery();
        
        database.addTerm("test1", "test2");
        
        PreparedStatement p2 = DriverManager.getConnection("jdbc:sqlite:chemistryTester.db").prepareStatement("SELECT COUNT(*) FROM Terms");
        ResultSet r2 = p2.executeQuery();
        
        assertTrue(r2.getInt("COUNT")>r.getInt("COUNT"));
    }
//    
    @Test
    public void canRemoveFromDatabase() throws SQLException {
        PreparedStatement p1 = DriverManager.getConnection("jdbc:sqlite:chemistryTester.db").prepareStatement("SELECT COUNT(*) FROM Terms");
        ResultSet r = p1.executeQuery();
        
        database.removeTerm("test1");
        
        PreparedStatement p2 = DriverManager.getConnection("jdbc:sqlite:chemistryTester.db").prepareStatement("SELECT COUNT(*) FROM Terms");
        ResultSet r2 = p2.executeQuery();
        
        assertTrue(r2.getInt("COUNT")<r.getInt("COUNT"));
    }
    
    
}
