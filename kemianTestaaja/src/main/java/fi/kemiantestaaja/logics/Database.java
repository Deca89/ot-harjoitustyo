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
/**
 * Sisältää metodeja, joilla käsitellään kyseisen kurssin/tietokannan sisältöä.
 * @param course kurssin/tietokannan nimi
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public Database(String course) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String check = course + ".db";
        this.dM = DriverManager.getConnection("jdbc:sqlite:" + check);
    }
/**
 * Metodi lisää termi/selitys parin kurssin tietokantaan
 * @param term lisättävä termi
 * @param explanation selitys termille
 * @return 1: termi on jo lisätty tietokantaan 2: termi ja selitys lisättiin tietokantaan 3: selityksen lisäyksessä tapahtui ongelma
 * @throws SQLException 
 */
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
/**
 * Metodi poistaa termi/selitys parin kurssin tietokannasta
 * @param term poistettava termi
 * @return 1: termiä ei ole tietokannassa 2: termi ja selitys poistettu 3: ongelma poistossa.
 * @throws SQLException 
 */
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
/**
 * Metodi palauttaa kurssin tietokannssa olevien termien määrän
 * @return tietokannassa olevien termien lukumäärä
 * @throws SQLException 
 */
    public int termsInDatabase() throws SQLException {
        PreparedStatement p1 = dM.prepareStatement("SELECT Count(*) AS C FROM Terms");
        ResultSet r = p1.executeQuery();

        return r.getInt("C");
    }
    /**
     * Metodi palauttaa kaikki tietokannassa olevat termit
     * @return palauttaa ArrayList:an kaikista termeistä
     * @throws SQLException 
     */
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
/**
 * Metodi palauttaa kaikki termit ja selitykset tietokannasta
 * @return palauttaa ArrayList:an kaikista termeistä ja selityksistä (termi, selitys, tyhjä rivi)
 * @throws SQLException 
 */
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
