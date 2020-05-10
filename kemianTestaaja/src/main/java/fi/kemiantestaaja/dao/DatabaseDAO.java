/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kurssin/tietokannan hallintaa
 *
 * @author Juuri
 */
public class DatabaseDAO {

    String course;

    /**
     * Luo hallintapohjan
     *
     * @param course tarkasteltava kurssi
     */
    public DatabaseDAO(String course) {
        this.course = course;
    }

    /**
     * luo yhteyden tietokantaan
     *
     * @return yhteys
     */
    public Connection createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection givenConnection = DriverManager.getConnection("jdbc:sqlite:" + course);
            return givenConnection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * lisää termi/selitys tietokantaan
     *
     * @param term termi
     * @param explanation selitys
     * @return true: lisäys onnistui false: lisäys epäonnistui
     */
    public boolean addTerm(String term, String explanation) {
        try {
            int toReturn = 0;
            Connection dM = createConnection();
            PreparedStatement p = dM.prepareStatement("INSERT INTO Terms(name, explanation) VALUES (?, ?)");
            p.setString(1, term);
            p.setString(2, explanation);
            p.executeUpdate();
            dM.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * poista termi tietokannasta
     *
     * @param term termin nimi
     * @return true: termin poisto onnistui false: termin poisto epäonnisti
     */
    public boolean removeTerm(String term) {
        try {
            Connection dM = createConnection();
            PreparedStatement p1 = dM.prepareStatement("SELECT id FROM Terms WHERE name=?");
            p1.setString(1, term);
            ResultSet r = p1.executeQuery();
            Integer termID = r.getInt("id");

            PreparedStatement p2 = dM.prepareStatement("DELETE FROM Terms WHERE id=?");
            p2.setInt(1, r.getInt("id"));
            p2.executeUpdate();
            dM.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Listaa kaikki termit tietokannassa
     *
     * @return lista termeistä, null jos termien haku epäonnistui
     */
    public ArrayList<String> listTerms() {
        try {
            ArrayList<String> toReturn = new ArrayList<>();
            Connection dM = createConnection();
            PreparedStatement p1 = dM.prepareStatement("SELECT T.name FROM Terms T");
            ResultSet r = p1.executeQuery();
            ResultSetMetaData rmd = r.getMetaData();

            while (r.next()) {
                toReturn.add(r.getString(1));
            }
            dM.close();
            return toReturn;
        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     * listaa kaikki termit ja selitykset tietokannasta
     *
     * @return lista termeistä ja selityksistä, null jos haku epäonnistui
     */
    public ArrayList<String> listTermsAndExplanations() {
        try {
            ArrayList<String> toReturn = new ArrayList<>();
            Connection dM = createConnection();
            PreparedStatement p1 = dM.prepareStatement("SELECT name, explanation FROM Terms");
            ResultSet r = p1.executeQuery();
            ResultSetMetaData rmd = r.getMetaData();

            while (r.next()) {
                toReturn.add(r.getString(1));
                toReturn.add(r.getString(2));
                toReturn.add(" ");
            }
            dM.close();
            return toReturn;
        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     * Palauttaa termien lukumäärän tietokannassa
     *
     * @return termien lukumäärä tietokannassa (numerona)
     */
    public int numberOfTerms() {
        try {
            Connection dM = createConnection();
            PreparedStatement p1 = dM.prepareStatement("SELECT Count(*) AS C FROM Terms");
            ResultSet r = p1.executeQuery();

            return r.getInt("C");
        } catch (SQLException ex) {
            return -1;
        }
    }

    /**
     * Palauttaa listan halutuissta termeistä tai selityksistä
     *
     * @param wantedWords lista haluttujen asioiden id:stä
     * @param wanted name - saadaan termit, explanation - saadaan selitykset
     * @return lista halutuista termeistä/selityksistä
     */
    public ArrayList<String> listTermsOrExplanations(ArrayList<Integer> wantedWords, String wanted) {
        ArrayList<String> wantedNames = new ArrayList<>();

        for (int i = 0; i < wantedWords.size(); i++) {
            try {
                Connection dM = createConnection();
                PreparedStatement p = dM.prepareStatement("SELECT " + wanted + " FROM Terms WHERE id=?");
                p.setInt(1, wantedWords.get(i));
                ResultSet r = p.executeQuery();
                wantedNames.add(r.getString(wanted));
            } catch (SQLException ex) {
                return null;
            }
        }

        return wantedNames;
    }

}
