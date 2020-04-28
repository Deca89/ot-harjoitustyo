/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.logics;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juuri
 */
public class Database {

    boolean databaseExists;
    Connection dM;

    public Database(String course) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String check = course + ".db";
        this.dM = DriverManager.getConnection("jdbc:sqlite:" + check);
    }

    public int addTerm(String term, String explanation) throws SQLException {
        int toReturn = 0;
        PreparedStatement p = dM.prepareStatement("INSERT INTO Terms(name) VALUES (?)");
        p.setString(1, term);
        try {
            p.executeUpdate();
        } catch (SQLException e) {
            return 1;
        }
        PreparedStatement p2 = dM.prepareStatement("SELECT id FROM Terms WHERE name=?");
        p2.setString(1, term);
        ResultSet r = p2.executeQuery();
        PreparedStatement p3 = dM.prepareStatement("INSERT INTO Explanations(Term_id, explanation) VALUES (?, ?)");
        p3.setString(2, explanation);
        p3.setInt(1, r.getInt("id"));

        try {
            p3.executeUpdate();
            return 2;
        } catch (SQLException e) {
            return 3;
        }

    }

    public Integer removeTerm(String term) throws SQLException {
        PreparedStatement p1 = dM.prepareStatement("SELECT id FROM Terms WHERE name=?");
        p1.setString(1, term);
        ResultSet r = p1.executeQuery();
        try {
            Integer termID = r.getInt("id");
        } catch (Exception e) {
            return 1;
        }

        PreparedStatement p2 = dM.prepareStatement("DELETE FROM Terms WHERE id=?");
        p2.setInt(1, r.getInt("id"));
        try {
            p2.executeUpdate();
            return 2;
        } catch (SQLException e) {
            return 3;
        }
    }

    public int termsInDatabase() throws SQLException {
        PreparedStatement p1 = dM.prepareStatement("SELECT Count(*) AS C FROM Terms");
        ResultSet r = p1.executeQuery();

        return r.getInt("C");
    }
    
    public ArrayList<String> getTerms() throws SQLException {
        ArrayList<String> toReturn = new ArrayList<>();
        PreparedStatement p1 = dM.prepareStatement("SELECT T.name FROM Terms T");
        ResultSet r = p1.executeQuery();
        ResultSetMetaData rmd = r.getMetaData();
        
        while (r.next()) {
            toReturn.add(r.getString(1));
        }
        
        return toReturn;
    }

    public ArrayList<String> printTermsAndExplanations() throws SQLException {
        ArrayList<String> toReturn = new ArrayList<>();
        PreparedStatement p1 = dM.prepareStatement("SELECT T.name, E.explanation FROM Terms T, Explanations E WHERE T.id=E.Term_id");
        ResultSet r = p1.executeQuery();
        ResultSetMetaData rmd = r.getMetaData();

        while (r.next()) {
            toReturn.add(r.getString(1));
            toReturn.add(r.getString(2));
            toReturn.add(" ");
        }
        
        return toReturn;
    }

}
