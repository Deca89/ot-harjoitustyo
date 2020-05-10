/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.kemiantestaaja.domain.CourseSelections;
import fi.kemiantestaaja.domain.Database;
import fi.kemiantestaaja.domain.Exam;
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
public class ExamTest {
    
    Database database;
    Exam exam;
    
    @Before
    public void setUp() {
        CourseSelections d = new CourseSelections();
        d.deleteCourse("test3");
        d.addCourse("test3");
        database = new Database("test3");
        this.database.addTerm("test1", "first term");
        exam = new Exam(1, database);
        exam.setTermNumbers();
    }
    
    @Test
    public void getTermsWorks() {
        ArrayList<String> terms = exam.getTerms();
        
        assertTrue(terms.get(0).equalsIgnoreCase("test1"));
    }
    
    @Test
    public void getExplanationsWorks() {
        ArrayList<String> explanations = exam.getExplanations();
        
        assertTrue(explanations.get(0).equalsIgnoreCase("first term"));
    }
    
    @Test
    public void canCheckExam() {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(0);
        
        int correctAnswers = exam.checkExam(answer);
        
        assertTrue(correctAnswers==1);
    }
    

}
