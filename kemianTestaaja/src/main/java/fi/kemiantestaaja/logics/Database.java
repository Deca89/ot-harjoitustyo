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

/**
 *
 * @author Juuri
 */
public class Database {

    boolean databaseExists;
    Connection dM;

    public Database() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        File file = new File("chemistryTester.db");
        if (file.exists()) {
            this.databaseExists = true;
            this.dM = DriverManager.getConnection("jdbc:sqlite:chemistryTester.db");
        } else {
            this.databaseExists = false;
            this.dM = null;
        }
    }

    public void addDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        if (databaseExists) {
            System.out.println("Tietokanta on jo olemassa\n");
        } else {
            Connection db = DriverManager.getConnection("jdbc:sqlite:chemistryTester.db");
            this.databaseExists = true;
            dM = db;

            System.out.println("Tietokanta luotu\n");

            dM.createStatement().execute("CREATE TABLE IF NOT EXISTS Terms(id INTEGER PRIMARY KEY, name TEXT UNIQUE NOT NULL)");
            dM.createStatement().execute("CREATE TABLE IF NOT EXISTS Explanations(Term_id INTEGER REFERENCES Terms ON DELETE CASCADE, explanation TEXT UNIQUE NOT NULL)");
        }
    }

    public boolean doesDatabaseExist() {
        if (!databaseExists) {
            System.out.println("\nTietokantaa ei ole vielä olemassa. \nYstävällisesti luo ensin tietokanta ja yritä sitten uudestaan.\n");
        }
        return databaseExists;
    }

    public void addTerm(String term, String explanation) throws SQLException {
        PreparedStatement p = dM.prepareStatement("INSERT INTO Terms(name) VALUES (?)");
        p.setString(1, term);
        try {
            p.executeUpdate();
            System.out.println(term + " lisätty termeihin\n");
        } catch (SQLException e) {
            System.out.println("Termi on jo lisätty tietokantaan. Yritä uudelleen.\n");
            return;
        }

        PreparedStatement p2 = dM.prepareStatement("SELECT id FROM Terms WHERE name=?");
        p2.setString(1, term);
        ResultSet r = p2.executeQuery();

        PreparedStatement p3 = dM.prepareStatement("INSERT INTO Explanations(Term_id, explanation) VALUES (?, ?)");
        p3.setString(2, explanation);
        p3.setInt(1, r.getInt("id"));

        try {
            p3.executeUpdate();
            System.out.println("Selitys lisätty myös.\n");
        } catch (SQLException e) {
            System.out.println("Selityksen lisäyksessä sattui virhe. En tiedä vielä miksi.\n");
        }

    }

    public void removeTerm(String term) throws SQLException {
        PreparedStatement p1 = dM.prepareStatement("SELECT id FROM Terms WHERE name=?");
        p1.setString(1, term);
        ResultSet r = p1.executeQuery();
        try {
            Integer termID = r.getInt("id");
        } catch (Exception e) {
            System.out.println("Termiä ei ole tietokannassa - sitä ei siis voida poistaa");
            return;
        }

        PreparedStatement p2 = dM.prepareStatement("DELETE FROM Terms WHERE id=?");
        p2.setInt(1, r.getInt("id"));
        try {
            p2.executeUpdate();
            System.out.println(term + " poistettu termeistä\n");
        } catch (SQLException e) {
            System.out.println("Termin poistaminen epäonnistui.\n");
        }

    }
    
    public void printTermsAndExplanations() throws SQLException {
        PreparedStatement p1 = dM.prepareStatement("SELECT T.name, E.explanation FROM Terms T, Explanations E WHERE T.id=E.Term_id");
        ResultSet r = p1.executeQuery();
        ResultSetMetaData rmd = r.getMetaData();
        
        while (r.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println(r.getString(i));
            }
            System.out.println("");
        }
    }

}
