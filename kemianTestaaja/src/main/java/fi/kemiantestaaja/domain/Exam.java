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
    private int possibleQuestions;
    private ArrayList<String> terms;
    private ArrayList<String> explanations;
    private ArrayList<Integer> answers;
    private Connection dM;

    public Exam(String course, int questionAmount, int possibleQuestions) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String check = course + ".db";
        this.dM = DriverManager.getConnection("jdbc:sqlite:" + check);
        this.questionAmount = questionAmount;
        this.possibleQuestions = possibleQuestions;
        this.terms = new ArrayList<>();
        this.explanations = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    public ArrayList<Integer> createExam() throws SQLException {
        for (int i = 1; i <= possibleQuestions; i++) {
            this.answers.add(i);
        }

        Collections.shuffle(answers);
        List<Integer> termss = answers.subList(0, questionAmount);
        ArrayList<Integer> termIds = new ArrayList<>();
        termIds.addAll(termss);
        Collections.shuffle(termIds);

        for (int i = 0; i < questionAmount; i++) {
            PreparedStatement p = dM.prepareStatement("SELECT name FROM Terms WHERE id=?");
            p.setInt(1, termIds.get(i));
            ResultSet r = p.executeQuery();
            String name = r.getString("name");

            PreparedStatement p2 = dM.prepareStatement("SELECT explanation FROM Explanations WHERE Term_id=?");
            p2.setInt(1, this.answers.get(i));
            ResultSet r2 = p2.executeQuery();
            String explanation = r2.getString("explanation");

            System.out.println(name + " __              " + (i + 1) + " " + explanation);
        }

        return termIds;
    }

    public void checkExam(ArrayList<Integer> givenAnswers) {
        int correct = 0;
        for (int i = 0; i < givenAnswers.size(); i++) {
            if (givenAnswers.get(i) == answers.get(i)) {
                correct++;
            }
        }

        System.out.println("Tulos: " + correct + "/" + questionAmount);
    }

}
