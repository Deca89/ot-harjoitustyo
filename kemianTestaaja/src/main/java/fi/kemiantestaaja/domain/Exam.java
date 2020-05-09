/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Juuri
 */
public class Exam {

    private int questionAmount;
    private Database database;
    private ArrayList<Integer> termNumbers;
    

    public Exam(int questionAmount, Database database) {
        this.questionAmount = questionAmount;
        this.database = database;
        this.termNumbers = new ArrayList<>();
        
    }
    
    public void setTermNumbers() {
        ArrayList<Integer> terms = new ArrayList<>();
        
        for (int i = 1; i <= database.numberOfTerms(); i++) {
            terms.add(i);
        }
        
        Collections.shuffle(terms);
        List<Integer> termss = terms.subList(0, questionAmount);
        this.termNumbers.addAll(termss);
    }
    
    public ArrayList<String> getTerms() {
        ArrayList<String> terms = database.getTermsOrExplanations(this.termNumbers, "name");
        return terms;
    }
    
    public ArrayList<String> getExplanations() {
        Collections.shuffle(termNumbers);
        ArrayList<String> explanations = database.getTermsOrExplanations(this.termNumbers, "explanation");
        return explanations;
    }

    public Integer checkExam(ArrayList<Integer> givenAnswers) {
        int correct = 0;
        for (int i = 0; i < givenAnswers.size(); i++) {
            if (givenAnswers.get(i) == termNumbers.get(i)) {
                correct++;
            }
        }
        return correct;
    }

}
